/*
 *  CUL4Java - Java API for CULfw wireless transceiver
 *  
 *  Copyright (C) 2011-2012  Tobias Wegner
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.tobiaswegner.communication.cul4java.impl;

public class FS20Constants {
	public static final int FS20DeviceBinarySwitch	= 0x01;
	public static final int FS20DeviceDimmer		= 0x02;
	
	public static final int FS20_Off				= 0x00;
	public static final int FS20_STEP_1				= 0x01;
	public static final int FS20_STEP_2				= 0x02;
	public static final int FS20_STEP_3				= 0x03;
	public static final int FS20_STEP_4				= 0x04;
	public static final int FS20_STEP_5				= 0x05;
	public static final int FS20_STEP_6				= 0x06;
	public static final int FS20_STEP_7				= 0x07;
	public static final int FS20_STEP_8				= 0x08;
	public static final int FS20_STEP_9				= 0x09;
	public static final int FS20_STEP_10			= 0x0A;
	public static final int FS20_STEP_11			= 0x0B;
	public static final int FS20_STEP_12			= 0x0C;
	public static final int FS20_STEP_13			= 0x0D;
	public static final int FS20_STEP_14			= 0x0E;
	public static final int FS20_STEP_15			= 0x0F;
	public static final int FS20_STEP_16			= 0x10;
	public static final int FS20_On					= 0x11;
	public static final int FS20_Toggle				= 0x12;
	public static final int FS20_DimmUp				= 0x13;
	public static final int FS20_DimmDown			= 0x14;
	public static final int FS20_DimmUpDown			= 0x15;
	public static final int FS20_TimeSet			= 0x16;
	public static final int FS20_SentStatus			= 0x17;
	public static final int FS20_TimerOff			= 0x18;
	public static final int FS20_TimerOn			= 0x19;
	public static final int FS20_TimerLastValue		= 0x1A;
	public static final int FS20_Reset				= 0x1B;	
}
