
package faceRecognizer;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.face.FaceRecognizer;
import org.opencv.face.LBPHFaceRecognizer;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class faceRecognizer {

	  static { 
			System.load("/home/gradle/faceRecognition/lib/libopencv_java340.so"); 
		}
    public void faceRecognition() {
        String trainingDir = "./trainFace/";

        Mat testImage = Imgcodecs.imread("testFace/1.jpg", Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
        // Comment out, this is for training purpose
        /*
        File trainImages = new File(trainingDir);
        FilenameFilter imgFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                name = name.toLowerCase();
                return name.endsWith(".jpg") || name.endsWith(".pgm") || name.endsWith(".png");
            }
        };
        File[] imageFiles =  trainImages.listFiles(imgFilter);
        List<Mat> images = new ArrayList<>(imageFiles.length);
        int[] labelsBuf =  new int[imageFiles.length];
        int counter = 0;

        for(File image : imageFiles) {
            Mat img = Imgcodecs.imread(image.getAbsolutePath(), Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
            int label = Integer.parseInt(image.getName().split("\\-")[0]);
            images.add(img);
            labelsBuf[counter] = label;
            counter++;
        }
        */

        LBPHFaceRecognizer face = LBPHFaceRecognizer.create();

        //face.train(images, new MatOfInt(labelsBuf));
        face.read("faceModel.yml");

        int[] label = new int[1];
        double[] confidence = new double[1];
        face.predict(testImage, label, confidence);
        int predictedLabel = label[0];
        System.out.println("Predicted label: " + predictedLabel);
    }
}
