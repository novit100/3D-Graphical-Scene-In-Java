package unittests;
import primitives.Color;
import renderer.ImageWriter;
import org.junit.Test;




public class ImageWriterTest
{
/**
 * 
 */
    @Test
    public void ImageWiterWriteToImageTest()
    {
        ImageWriter imageWriter = new ImageWriter("our1", 800, 500);
        int Nx = imageWriter.getNx();
        int Ny = imageWriter.getNy();
        for (int i = 0; i < Ny; i++) {
            for (int j = 0; j < Nx; j++) {
                if (i % 50 == 0 || j % 50 == 0)
                {
                    imageWriter.writePixel(j, i, Color.GREEN);
                }
                else
                {
                    imageWriter.writePixel(j, i, Color.RED);
                }
            }
        }
        imageWriter.writeToImage();
    }
}