package amidst.mojangapi.file;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URLClassLoader;

import amidst.documentation.Immutable;
import amidst.mojangapi.file.directory.DotMinecraftDirectory;
import amidst.mojangapi.file.directory.ProfileDirectory;
import amidst.mojangapi.file.directory.VersionDirectory;
import amidst.mojangapi.file.json.version.VersionJson;
import amidst.mojangapi.file.service.ClassLoaderService;

@Immutable
public class LauncherProfile {
	public final ClassLoaderService classLoaderService;
	public final DotMinecraftDirectory dotMinecraftDirectory;
	public final ProfileDirectory profileDirectory;
	public final VersionDirectory versionDirectory;
	public final VersionJson versionJson;
	public final boolean isVersionListedInProfile;
	public final String profileName;

	public LauncherProfile(
			DotMinecraftDirectory dotMinecraftDirectory,
			ProfileDirectory profileDirectory,
			VersionDirectory versionDirectory,
			VersionJson versionJson,
			boolean isVersionListedInProfile,
			String profileName) {
		this(new ClassLoaderService(), dotMinecraftDirectory, profileDirectory, versionDirectory, versionJson, isVersionListedInProfile, profileName);
	}

	public LauncherProfile(
			ClassLoaderService classLoaderService,
			DotMinecraftDirectory dotMinecraftDirectory,
			ProfileDirectory profileDirectory,
			VersionDirectory versionDirectory,
			VersionJson versionJson,
			boolean isVersionListedInProfile,
			String profileName) {
		this.classLoaderService = classLoaderService;
		this.dotMinecraftDirectory = dotMinecraftDirectory;
		this.profileDirectory = profileDirectory;
		this.versionDirectory = versionDirectory;
		this.versionJson = versionJson;
		this.isVersionListedInProfile = isVersionListedInProfile;
		this.profileName = profileName;
	}

	public String getVersionId() {
		return versionJson.getId();
	}

	/**
	 * True, iff the contained version was listed in the profile. Especially,
	 * this is false if a modded profiles was resolved via
	 * {@link UnresolvedLauncherProfile#resolveToVanilla(VersionList)}.
	 */
	public boolean isVersionListedInProfile() {
		return isVersionListedInProfile;
	}

	public String getVersionName() {
		return getVersionNamePrefix() + versionJson.getId();
	}

	private String getVersionNamePrefix() {
		if (isVersionListedInProfile) {
			return "";
		} else {
			return "*";
		}
	}

	public String getProfileName() {
		return profileName;
	}

	public File getJar() {
		return versionDirectory.getJar();
	}

	public File getSaves() {
		return profileDirectory.getSaves();
	}

	public URLClassLoader newClassLoader() throws MalformedURLException {
		return classLoaderService.createClassLoader(
				dotMinecraftDirectory.getLibraries(),
				versionJson.getLibraries(),
				versionDirectory.getJar());
	}
}
