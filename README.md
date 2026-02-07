# PDF Compressor Merger

## Terminology

Originally meant to simply merge PDFs, this tool also acts as a compression utility, allowing you to merge multiple PDF
files into one **uniformly scaled** document while reducing their file size through downscaling and image recompression.

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

---

Run again for new sets of files:

```
java -jar pdf.jar thesis.pdf part1.pdf part2.pdf notes.pdf
```

Your final compressed PDF is ready to share or archive.

### Downloads

You can download the latest jar file
from [my build server](https://build.florianreuth.de/job/PDFCompressorMerger), [GitHub Actions](https://github.com/florianreuth/PDFCompressorMerger/actions)
or use the [releases tab](https://github.com/florianreuth/PDFCompressorMerger/releases).

## Contact

If you encounter any issues, please report them on the
[issue tracker](https://github.com/florianreuth/PDFCompressorMerger/issues).
If you just want to talk or need help with PDFCompressorMerger, feel free to join my
[Discord](http://florianreuth.de/discord).
