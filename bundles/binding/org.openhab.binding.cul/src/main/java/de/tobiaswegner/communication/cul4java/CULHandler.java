package de.tobiaswegner.communication.cul4java;

public interface CULHandler<L extends CULListener> {

	public void parseData(String receivedLine);

	public char getCULReceiverPrefix();

	public void registerListener(L listener);

	public void unregisterListener(L listener);
}
