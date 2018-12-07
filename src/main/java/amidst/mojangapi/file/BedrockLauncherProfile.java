package amidst.mojangapi.file;

import amidst.Amidst;
import amidst.documentation.Immutable;
import amidst.mojangapi.file.json.version.LibraryJson;
import amidst.mojangapi.file.service.ClassLoaderService;
import amidst.parsing.FormatException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;


@Immutable
public class BedrockLauncherProfile extends LauncherProfile {

    public BedrockLauncherProfile(LauncherProfile javaProfile, File bedrockified) {
        super(
                new BedrockClassLoaderService(bedrockified),
                javaProfile.dotMinecraftDirectory,
                javaProfile.profileDirectory,
                javaProfile.versionDirectory,
                javaProfile.versionJson,
                javaProfile.isVersionListedInProfile,
                javaProfile.profileName + " Bedrockified");
    }

    @Immutable
    private static class BedrockClassLoaderService extends ClassLoaderService {

        private final URL bedrockified;

        public BedrockClassLoaderService(File bedrockified) {
            try {
                this.bedrockified = bedrockified.toURI().toURL();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected List<URL> getAllClassLoaderUrls(File librariesDirectory, List<LibraryJson> libraries, File versionJarFile) throws MalformedURLException {
            File unsignedJarFile = new File(versionJarFile.getParentFile(), versionJarFile.getName() + "_unsigned");
            if (!unsignedJarFile.exists()) {
                try {
                    ZipFile zipIn = new ZipFile(versionJarFile);
                    ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(unsignedJarFile));
                    Enumeration<? extends ZipEntry> entries = zipIn.entries();
                    while (entries.hasMoreElements()) {
                        ZipEntry entryIn = entries.nextElement();
                        if (entryIn.getName().startsWith("META-INF/"))
                            continue;
                        ZipEntry entryOut = new ZipEntry(entryIn.getName());
                        zipOut.putNextEntry(entryOut);
                        if (!entryIn.getName().endsWith("/")) {
                            byte[] buf = new byte[8192];
                            int n;
                            InputStream in = zipIn.getInputStream(entryIn);
                            while ((n = in.read(buf)) != -1)
                                zipOut.write(buf, 0, n);
                        }
                        zipOut.closeEntry();
                    }
                    zipIn.close();
                    zipOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            List<URL> urls = new ArrayList<>(super.getAllClassLoaderUrls(librariesDirectory, libraries, unsignedJarFile));
            urls.add(0, bedrockified);
            return urls;
        }
    }

    public static class Unresolved extends UnresolvedLauncherProfile {
        public Unresolved(UnresolvedLauncherProfile other) {
            super(other.dotMinecraftDirectory, other.launcherProfileJson);
        }

        @Override
        public String getName() {
            return "Bedrockified";
        }

        @Override
        public LauncherProfile resolveToVanilla(VersionList versionList) throws FormatException, IOException {
            LauncherProfile javaProfile = super.resolveToVanilla(versionList);
            File bedrockified = Amidst.findBedrockified();
            if (bedrockified == null)
                throw new FormatException("No Bedrockified found");
            return new BedrockLauncherProfile(javaProfile, bedrockified);
        }
    }
}
