/*
 * #%L
 * SciJava Common shared library for SciJava software.
 * %%
 * Copyright (C) 2009 - 2021 SciJava developers.
 * %%
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */

package org.scijava.io.event;

import org.scijava.event.SciJavaEvent;
import org.scijava.io.location.FileLocation;
import org.scijava.io.location.Location;

/**
 * An event indicating that I/O (e.g., opening or saving) has occurred.
 * 
 * @author Curtis Rueden
 */
public abstract class IOEvent extends SciJavaEvent {

	/** The data location (source or destination). */
	private final Location location;

	/** The data for which I/O took place. */
	private final Object data;

	/**
	 * @deprecated use {@link #IOEvent(Location, Object)} instead
	 */
	@Deprecated
	public IOEvent(final String descriptor, final Object data) {
		this(new FileLocation(descriptor), data);
	}

	public IOEvent(final Location location, final Object data) {
		this.location = location;
		this.data = data;
	}

	/** Gets the data location (source or destination). */
	public Location getLocation() {
		return location;
	}

	/** Gets the data for which I/O took place. */
	public Object getData() {
		return data;
	}

	// -- Object methods --

	@Override
	public String toString() {
		return super.toString() + "\n\tlocation = " + location + "\n\tdata = " +
			data;
	}

	/**
	 * @deprecated use {@link #getLocation()} instead
	 */
	@Deprecated
	public String getDescriptor() {
		try {
			FileLocation fileLocation = (FileLocation) getLocation();
			return fileLocation.getFile().getAbsolutePath();
		} catch(ClassCastException e) {
			return getLocation().getURI().toString();
		}
	}

}
