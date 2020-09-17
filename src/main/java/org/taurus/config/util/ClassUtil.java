package org.taurus.config.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
//import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.taurus.entity.sys.TSUrlEntity;

/**
 * 类相关的工具类
 * 
 * @author <a href="mailto:ohergal@gmail.com">ohergal</a>
 * 
 */
public class ClassUtil {

	/**
	 * 取得某个接口下所有实现这个接口的类
	 * 
	 * @param c
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Class> getAllClassByInterface(Class c) {
		List<Class> returnClassList = null;

		if (c.isInterface()) {
			// a获取当前的包名
			String packageName = c.getPackage().getName();
			// a获取当前包下以及子包下所以的类
			List<Class<?>> allClass = getClasses(packageName);
			if (allClass != null) {
				returnClassList = new ArrayList<Class>();
				for (Class classes : allClass) {
					// a判断是否是同一个接口
					if (c.isAssignableFrom(classes)) {
						// a本身不加入进去
						if (!c.equals(classes)) {
							returnClassList.add(classes);
						}
					}
				}
			}
		}

		return returnClassList;
	}

	/**
	 * 取得某一类所在包的所有类名 不含迭代
	 * 
	 * @param classLocation
	 * @param packageName
	 * @return
	 */
	public static String[] getPackageAllClassName(String classLocation, String packageName) {
		// a将packageName分解
		String[] packagePathSplit = packageName.split("[.]");
		String realClassLocation = classLocation;
		int packageLength = packagePathSplit.length;
		for (int i = 0; i < packageLength; i++) {
			realClassLocation = realClassLocation + File.separator + packagePathSplit[i];
		}
		File packeageDir = new File(realClassLocation);
		if (packeageDir.isDirectory()) {
			String[] allClassName = packeageDir.list();
			return allClassName;
		}
		return null;
	}

	/**
	 * 从包package中获取所有的Class
	 * 
	 * @param packageName
	 * @return
	 */
	public static List<Class<?>> getClasses(String packageName) {

		// a第一个class类的集合
		List<Class<?>> classes = new ArrayList<Class<?>>();
		// a是否循环迭代
		boolean recursive = true;
		// a获取包的名字 并进行替换
		String packageDirName = packageName.replace('.', '/');
		// a定义一个枚举的集合 并进行循环来处理这个目录下的things
		Enumeration<URL> dirs;
		try {
			dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
			// a循环迭代下去
			while (dirs.hasMoreElements()) {
				// a获取下一个元素
				URL url = dirs.nextElement();
				// a得到协议的名称
				String protocol = url.getProtocol();
				// a如果是以文件的形式保存在服务器上
				if ("file".equals(protocol)) {
					// a获取包的物理路径
					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
					// a以文件的方式扫描整个包下的文件 并添加到集合中
					findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);
				} else if ("jar".equals(protocol)) {
					// a如果是jar包文件
					// a定义一个JarFile
					JarFile jar;
					try {
						// a获取jar
						jar = ((JarURLConnection) url.openConnection()).getJarFile();
						// a从此jar包 得到一个枚举类
						Enumeration<JarEntry> entries = jar.entries();
						// a同样的进行循环迭代
						while (entries.hasMoreElements()) {
							// a获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
							JarEntry entry = entries.nextElement();
							String name = entry.getName();
							// a如果是以/开头的
							if (name.charAt(0) == '/') {
								// a获取后面的字符串
								name = name.substring(1);
							}
							// a如果前半部分和定义的包名相同
							if (name.startsWith(packageDirName)) {
								int idx = name.lastIndexOf('/');
								// a如果以"/"结尾 是一个包
								if (idx != -1) {
									// a获取包名 把"/"替换成"."
									packageName = name.substring(0, idx).replace('/', '.');
								}
								// a如果可以迭代下去 并且是一个包
								if ((idx != -1) || recursive) {
									// a如果是一个.class文件 而且不是目录
									if (name.endsWith(".class") && !entry.isDirectory()) {
										// a去掉后面的".class" 获取真正的类名
										String className = name.substring(packageName.length() + 1, name.length() - 6);
										try {
											// a添加到classes
											classes.add(Class.forName(packageName + '.' + className));
										} catch (ClassNotFoundException e) {
											e.printStackTrace();
										}
									}
								}
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return classes;
	}

	/**
	 * 以文件的形式来获取包下的所有Class
	 * 
	 * @param packageName
	 * @param packagePath
	 * @param recursive   是否循环迭代
	 * @param classes
	 */
	public static void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive,
			List<Class<?>> classes) {
		// a获取此包的目录 建立一个File
		File dir = new File(packagePath);
		// a如果不存在或者 也不是目录就直接返回
		if (!dir.exists() || !dir.isDirectory()) {
			return;
		}
		// a如果存在 就获取包下的所有文件 包括目录
		File[] dirfiles = dir.listFiles(new FileFilter() {
			// a自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
			public boolean accept(File file) {
				return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));
			}
		});
		// a循环所有文件
		for (File file : dirfiles) {
			// a如果是目录 则继续扫描
			if (file.isDirectory()) {
				findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive,
						classes);
			} else {
				// a如果是java类文件 去掉后面的.class 只留下类名
				String className = file.getName().substring(0, file.getName().length() - 6);
				try {
					// a添加到集合中去
					classes.add(Class.forName(packageName + '.' + className));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 获取全部的请求 路径(@RequestMapping的value)
	 * @param packageName
	 * @return
	 */
	public static List<TSUrlEntity> getAllRequestUrl(String packageName) {
		List<TSUrlEntity> allRequestUrl = new ArrayList<TSUrlEntity>();
		// a从包package中获取所有的Class
		List<Class<?>> classes = getClasses(packageName);
		for (Class<?> c : classes) {

			// a类
			RequestMapping annotation1 = c.getAnnotation(RequestMapping.class);
			String classRequestMapping = "";
			if (annotation1 != null) {
				String[] value = annotation1.value();
				classRequestMapping += (value != null && value.length > 0) ? value[0] : "";
			}

			// a变量
//			Field[] fs = c.getDeclaredFields();
//			for (Field f : fs) {
//				if (f.isAnnotationPresent(RequestMapping.class)) {
//					RequestMapping annotation = f.getAnnotation(RequestMapping.class);
//					String[] value = annotation.value();
//					String requestMapping = (value != null && value.length > 0) ? value[0] : "";
//					System.err.println(f.getName() + "(" + classRequestMapping + requestMapping + ")");
//				}
//			}

			// a方法
			Method[] methods = c.getDeclaredMethods();
			for (Method method : methods) {
				if (method.isAnnotationPresent(RequestMapping.class)) {
					RequestMapping annotation = method.getAnnotation(RequestMapping.class);
					String[] value = annotation.value();
					RequestMethod[] methodType = annotation.method();
					
					String methodRequestMapping = (value != null && value.length > 0) ? value[0] : "";
					String urlPath = classRequestMapping + methodRequestMapping;
					
					TSUrlEntity urlEntity = new TSUrlEntity();
					urlEntity.setUrlPath(urlPath);
					urlEntity.setUrlMethod(JsonUtil.arrayToJson(methodType));
					allRequestUrl.add(urlEntity);
				}
			}
		}
		return allRequestUrl;
	}
}
