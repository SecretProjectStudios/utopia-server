package secretprojectstudios;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayOutputStream;

public class ResourceUtilities {

    public static String encodeToQr(String source) {
        ByteArrayOutputStream baos = QRCode.from(source).to(ImageType.PNG).stream();
        return "data:image/png;base64," + DatatypeConverter.printBase64Binary(baos.toByteArray());
    }
}
