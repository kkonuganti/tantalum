/**
 * 
 */
package com.tantalum;

import java.util.UUID;

/**
 * @author k.konuganti
 *
 */
public class IDModel {

	private Object prefix;
	private UUID id;
	private Object suffix;

	public IDModel(Object prefix,Object suffix) {
		super();
		this.prefix=prefix;
		this.id = UUID.randomUUID();
		this.suffix=suffix;
	}

	
	public String getId() {
		return toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return  prefix + "-"+ id + "-"+suffix;
	}
	
	
}
