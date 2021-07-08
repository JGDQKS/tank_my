package test;
import org.junit.jupiter.api.Test;


import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class ImageTestTest {
    @Test
    void test(){

        try{
            BufferedImage image = ImageIO.read(new File("C:\\Users\\lenovo\\IdeaProjects\\mahsibing tank\\tank\\src\\images"));
            assertNotNull(image);

            BufferedImage images = ImageIO.read(ImageTestTest.class.getClassLoader().getResource("images/bulletD.gif"));
        }catch (IOException e){
            e.printStackTrace();
        }

        //fail("Not yet implemented");

    }

}