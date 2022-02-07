package tech.sushnag22.doghouse.ui.views.image;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import tech.sushnag22.doghouse.backend.service.ImageService;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.router.Route;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;

import com.vaadin.flow.router.PageTitle;
import tech.sushnag22.doghouse.ui.MainLayout;

@Route(value = "add-image", layout = MainLayout.class)
@PageTitle("Add Dog Image")
public class ImageView extends VerticalLayout {

    private Upload upload;
    private VerticalLayout imageContainer;

    @Autowired
    private ImageService imageService;

    public ImageView() {
        initImageContainer();
    }

    private void initUploaderImage() {
        MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();
        upload = new Upload(buffer);
        upload.setAcceptedFileTypes("image/jpeg","image/jpg", "image/png", "image/gif");

        upload.addSucceededListener(event -> {
            String attachmentName = event.getFileName();
            try {
                BufferedImage inputImage = ImageIO.read(buffer.getInputStream(attachmentName));
                ByteArrayOutputStream pngContent = new ByteArrayOutputStream();
                ImageIO.write(inputImage, "png", pngContent);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        add(upload);
    }

    private void initImageContainer(){
        imageContainer = new VerticalLayout();
        imageContainer.setWidth("200px");
        imageContainer.setHeight("200px");
        imageContainer.getStyle().set("overflow-x", "auto");
        add(imageContainer);
    }

}