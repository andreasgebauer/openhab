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

import java.io.IOException;

public interface CULInterface {

    public void close();

    public boolean isOpen();

    public void open(String deviceName) throws Exception;

    public void decode(String cmdLine);

    public void sendRAW(String sendString);

    public void registerHandler(CULHandler<?> handler);

    public void unregisterHandler(CULHandler<?> handler);

    public void unregisterHandler(char type);

    public <H extends CULHandler<?>> H getHandlerForType(char type);

    public void setOwnHouseCode(String housecode) throws IOException;

}
