/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package temp.jaxb;

import java.io.File;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author ribadas
 */
public class XmlFaqLoader {

    public static void main(String[] args) throws JAXBException {

        crearFichero();

        Unmarshaller um = null;
        JAXBContext context = null;

        context = JAXBContext.newInstance(FaqFile.class);
        um = context.createUnmarshaller();
        FaqFile faqFile = (FaqFile) um.unmarshal(new File("/tmp/faq_pruebas.xml"));

        for (Faq faq : faqFile.getFaqs()) {
            System.out.println(faq);
        }

    }

    private static void crearFichero() throws JAXBException {

        FaqFile faqFile = new FaqFile("/tmp/faq_pruebas.xml", "en");
        faqFile.setFaqs(new ArrayList<Faq>());
        faqFile.getFaqs().add(new Faq(1L,
                "How Do I Remove (or Change) the Colors in the ls Display?",
                "If ls is displaying in color and you haven't told it to, you probably have an alias configured for it. Some distributions ship this way by default."
                + "\nThe shell command, unalias ls, should completely unset the configuration that some distributions provide as standard."
                + "\nTo permanently make this change, check your initialization script, .bashrc."));
        faqFile.getFaqs().add(new Faq(2L,
                "Why Won't a Program Work in the Current Directory?",
                "Because the current directory (i.e., .) is not in the search path, for security reasons, as well as to insure that the correct program versions are used. If an intruder is able to write a file to a world-writable directory, like /tmp, presumably he or she would be able to execute it if the directory were in the search path. The solution to this is to include the directory in the command; e.g., ./myprog, instead of myprog. Or add the current directory to your PATH environment variable; e.g., export PATH=\".:\"$PATH using bash, although this is discouraged for the reasons mentioned above. "));
        faqFile.getFaqs().add(new Faq(3L,
                "How Do I Find Out If a Notebook Runs Linux?",
                "There's no fixed answer to this question, because notebook hardware is constantly updated, and getting the X display, sound, PCMCIA, modem, and so forth, working, can take a good deal of effort."
                + "\nMost notebooks currently on the market, for example, use \"Winmodems,\" which often do not work with Linux because of their proprietary hardware interfaces. Even notebooks which are certified as \"Linux compatible,\" may not be completely compatible."));
        faqFile.getFaqs().add(new Faq(4L,
                "Can Can I Resume an Interrupted Download?",
                " You can use the reget command of the standard ftp client program after reconnecting to pick up where you left off."
                + "\nClients like ncftp support resumed FTP downloads, and wget supports resumed FTP and HTTP downloads. "));


        JAXBContext context = JAXBContext.newInstance(FaqFile.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(faqFile, new File("/tmp/faq_pruebas.xml"));

    }
}
