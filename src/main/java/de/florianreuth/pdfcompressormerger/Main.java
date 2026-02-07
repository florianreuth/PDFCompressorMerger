/*
 * This file is part of PDFCompressorMerger - https://github.com/florianreuth/PDFCompressorMerger
 * Copyright (C) 2025-2026 Florian Reuth <git@florianreuth.de> and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.florianreuth.pdfcompressormerger;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.PDFRenderer;

public final class Main {

    private static final PDRectangle TARGET_SIZE = PDRectangle.A4;

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Usage: java -jar pdf.jar output.pdf input1.pdf input2.pdf ...");
            return;
        }

        final String outputFile = args[0];

        final PDFMergerUtility merger = new PDFMergerUtility();
        for (int i = 1; i < args.length; i++) {
            merger.addSource(new File(args[i]));
        }
        merger.setDestinationFileName("merged_temp.pdf");
        merger.mergeDocuments(null);

        try (final PDDocument merged = PDDocument.load(new File("merged_temp.pdf"));
             final PDDocument compressed = new PDDocument()) {

            final PDFRenderer renderer = new PDFRenderer(merged);

            for (int i = 0; i < merged.getNumberOfPages(); i++) {
                // Render page as image with better DPI (150–200)
                final BufferedImage bim = renderer.renderImageWithDPI(i, 180);

                final File tempImg = new File("page_" + i + ".jpg");
                ImageIO.write(bim, "jpg", tempImg);

                final PDPage page = new PDPage(TARGET_SIZE);
                compressed.addPage(page);

                final PDImageXObject pdImage = PDImageXObject.createFromFileByExtension(tempImg, compressed);
                try (final PDPageContentStream cs = new PDPageContentStream(compressed, page)) {
                    final float scale = Math.min(
                        TARGET_SIZE.getWidth() / pdImage.getWidth(),
                        TARGET_SIZE.getHeight() / pdImage.getHeight()
                    );
                    final float w = pdImage.getWidth() * scale;
                    final float h = pdImage.getHeight() * scale;
                    final float x = (TARGET_SIZE.getWidth() - w) / 2;  // center horizontally
                    final float y = (TARGET_SIZE.getHeight() - h) / 2; // center vertically
                    cs.drawImage(pdImage, x, y, w, h);
                }

                tempImg.delete();
            }

            compressed.save(outputFile);
            System.out.println("PDFs merged, scaled to A4 -> " + outputFile);
        }

        new File("merged_temp.pdf").delete();
    }

}
