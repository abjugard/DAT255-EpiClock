/*
 * Copyright (C) 2012 Joakim Persson, Daniel Augurell, Adrian Bjug�rd, Andreas Rol�n
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.chalmers.dat255.group09.Alarmed.modules.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import edu.chalmers.dat255.group09.Alarmed.modules.mathModule.activity.MathActivity;
import edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.activity.MemoryActivity;

/**
 * A factory class for fetching the different kind of AlarmActivation modules.
 * 
 * @author Joakim Persson
 * 
 */
public final class ModuleFactory {

	/**
	 * A singleton and cannot be instantiated.
	 */
	private ModuleFactory() {
	}

	private static Map<String, Class<?>> modules;

	static {
		modules = new HashMap<String, Class<?>>();
		modules.put("MathModule", MathActivity.class);
		modules.put("MemoryModule", MemoryActivity.class);
	}

	/**
	 * Get the names of all the modules used in this application.
	 * 
	 * @return An array of all the module names.
	 */
	public static String[] getModuleNames() {
		Set<String> names = modules.keySet();
		return names.toArray(new String[0]);
	}

	/**
	 * Load a new module.
	 * 
	 * @param moduleName
	 *            The name of the module.
	 * @return The selected module.
	 */
	public static Class<?> getModule(String moduleName) {
		return modules.get(moduleName);
	}

}
