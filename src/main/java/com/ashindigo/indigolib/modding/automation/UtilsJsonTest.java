package com.ashindigo.indigolib.modding.automation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;

/**
 * Used to test mods for the @UtilsJson annotation
 * @see UtilsJson
 * @author Ash Indigo
 * @deprecated Unused and probably not to fast. And the annotation doesnt exist anymore
 */
@Deprecated
public class UtilsJsonTest {
	
	// Various arrays for marking files
	static ArrayList<File> jars = new ArrayList<File>();
	static File assets = new File(UtilsJsonTest.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "/");
	static File bin = new File(assets.getParentFile().getParentFile().getParentFile().getParentFile().toString() + "/");
	public static ArrayList<File> markedJars = new ArrayList<File>();

	/**
	 * Tests all jars in the mod folder for the UtilsJson annotation.
	 * @see UtilsJson
	 */
	public static ArrayList<File> init(Class<? extends Annotation> annotation, ArrayList<File> jarList) {
		try {
		URL[] binFileArray = { bin.toURI().toURL() };
		URLClassLoader urlClass = new URLClassLoader(binFileArray, UtilsJsonTest.class.getClassLoader());
		File[] files = bin.listFiles();
		// Scans for files in directory
		int runtime = 0;
		while (runtime < files.length) {
			jars.add(files[runtime]);
			runtime++;
		}
		// Gets all jar files
		int runtime2 = 0;
		while (runtime2 < jars.size()) {
			File fileTest = (File) jars.get(runtime2);
			if (fileTest.getName().endsWith(".jar") == true) {
				markedJars.add(jars.get(runtime2));
			}
			runtime2++;
		}
		// Tests classes for the specified annotation annotation
		int runtime3 = 0;
		while (runtime3  < markedJars.size()) {
			FileInputStream fis = new FileInputStream(markedJars.get(runtime3).toString());
			JarInputStream jis = new JarInputStream(fis);
			JarFile jar = new JarFile(markedJars.get(runtime3).toString());
			Enumeration<?> em = jar.entries();
				while (em.hasMoreElements() == true) {
					String clazz = em.nextElement().toString();
					if (clazz.endsWith(".class")) {
						Class<?> obj = urlClass.loadClass(clazz.replace("/", ".").replace(".class", ""));
						if (obj.isAnnotationPresent(annotation)) {
							jarList.add(markedJars.get(runtime3));
						}
					}
			}
			fis.close();
			jis.close();
			jar.close();
			runtime3++;
		}
		urlClass.close();
				
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return jarList;
	}

}
