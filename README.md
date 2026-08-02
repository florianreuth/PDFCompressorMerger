# PDF Compressor Merger

This tool acts as a compression utility, allowing you to merge multiple PDF files into one **uniformly scaled**
document while reducing their file size through downscaling and image recompression.

## Usage

Set up your merged file using

```
java -jar pdf.jar output.pdf input1.pdf input2.pdf ...
```

All input PDFs will be combined, normalized to **A4 page size**, and compressed to a smaller, portable output file.

Add more files by simply appending them to the command:

```
java -jar pdf.jar final.pdf chapter1.pdf chapter2.pdf appendix.pdf
```

This will create a single merged and compressed file `final.pdf`.

To view your file, just open the resulting PDF with any standard PDF reader.

Run again for new sets of files:

```
java -jar pdf.jar thesis.pdf part1.pdf part2.pdf notes.pdf
```

Your final compressed PDF is ready to share or archive.

## Links

- Releases: https://github.com/florianreuth/PDFCompressorMerger/releases
- Dev builds: https://build.florianreuth.de/job/PDFCompressorMerger

## Contact

- Issues: https://github.com/florianreuth/PDFCompressorMerger/issues
- Discord: https://florianreuth.de/discord
