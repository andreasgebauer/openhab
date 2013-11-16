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

package de.tobiaswegner.communication.cul4java;


public interface FHTListener extends CULListener {
	public void receivedFHTState (String device, FHTState state);
	
	public void receivedFHTValue (String device, FHTEvent event, double temperature);
	
	public void receivedActuatorStatus(String device, int actuatorNumer, double opening);
	
	
}
