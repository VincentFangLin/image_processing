import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        String file = "C:/Users/Vibrant/Desktop/openCV/img1.tif";
        Mat src = Imgcodecs.imread(file, Imgcodecs.IMREAD_ANYDEPTH);
        //Creating destination matrix
        System.out.println("rows " + src.rows());
        System.out.println("cols " + src.cols());
        System.out.println("type " + src.type());
        System.out.println(src);
        System.out.println("channels " + src.channels());
        System.out.println("size " + src.size());
        System.out.println(Arrays.toString(src.get(207,519)));//(y,x)
        int count = 0;
        for (int x =0; x<src.cols(); x++) {
            for (int y=0; y<src.rows(); y++) {
//                double[] data = src.get(i, j); //Stores element in an array
                if (src.get(y,x)[0] > 60000) {
                    System.out.println("index:" + (y * src.cols() + x) + " X:" + x + " Y:" + y + " value:" + Arrays.toString(src.get(y, x)));
                }
            }
        }
//
//        HighGui.imshow("Image", src);
//        HighGui.waitKey();
//
//        JFrame frame = new JFrame("IMG");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        frame.setResizable(true);
//        frame.setLocationRelativeTo(null);
//        ImageIcon image = new ImageIcon(createAwtImage(src));
//        frame.setSize(image.getIconWidth()+10,image.getIconHeight()+35);
//
//        JLabel label1 = new JLabel(" ", image, JLabel.CENTER);
//        frame.getContentPane().add(label1);
//
//        frame.validate();
//        frame.setVisible(true);
    }
    public static BufferedImage createAwtImage(Mat mat) {

        int type = 0;
        if (mat.channels() == 1) {
            type = BufferedImage.TYPE_BYTE_GRAY;
        } else if (mat.channels() == 3) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        } else {
            return null;
        }

        BufferedImage image = new BufferedImage(mat.width(), mat.height(), type);
        WritableRaster raster = image.getRaster();
        DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
        byte[] data = dataBuffer.getData();
        mat.get(0, 0, data);

        return image;
    }
}
