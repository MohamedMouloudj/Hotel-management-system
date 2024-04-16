package view;

import javax.swing.ImageIcon;

public class ImageNotFoundedException extends RuntimeException {
    public ImageNotFoundedException(String message) {
        super(message);
    }

    public static void imageNotFound(ImageIcon icon) {
        if (icon == null)
            throw new ImageNotFoundedException("Program icon is not found");
    }
}
