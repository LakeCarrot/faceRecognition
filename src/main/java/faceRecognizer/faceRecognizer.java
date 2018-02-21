
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
			System.load("/home/carrot/faceRecognition/lib/libopencv_java340.so"); 
		}
		static LBPHFaceRecognizer face = LBPHFaceRecognizer.create();
		public void load() {
			face.read("faceModel.yml");
		}
    public void recognize(String filename) {
        Mat testImage = Imgcodecs.imread(filename, Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
        int[] label = new int[1];
        double[] confidence = new double[1];
        face.predict(testImage, label, confidence);
        int predictedLabel = label[0];
    }
}
