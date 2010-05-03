/*
 * Copyright (c) 2004 by Cosylab
 *
 * The full license specifying the redistribution, modification, usage and other
 * rights and obligations is included with the distribution of this project in
 * the file "LICENSE-CAJ". If the license is not included visit Cosylab web site,
 * <http://www.cosylab.com>.
 *
 * THIS SOFTWARE IS PROVIDED AS-IS WITHOUT WARRANTY OF ANY KIND, NOT EVEN THE
 * IMPLIED WARRANTY OF MERCHANTABILITY. THE AUTHOR OF THIS SOFTWARE, ASSUMES
 * _NO_ RESPONSIBILITY FOR ANY CONSEQUENCE RESULTING FROM THE USE, MODIFICATION,
 * OR REDISTRIBUTION OF THIS SOFTWARE.
 */

package org.epics.ca.server.impl.remote.handlers;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

import org.epics.ca.impl.remote.Transport;
import org.epics.ca.impl.remote.TransportSendControl;
import org.epics.ca.impl.remote.TransportSender;
import org.epics.ca.server.impl.remote.ServerContextImpl;


/**
 * Echo request handler.
 * @author <a href="mailto:matej.sekoranjaATcosylab.com">Matej Sekoranja</a>
 * @version $Id$
 */
public class EchoHandler extends AbstractServerResponseHandler {

	/**
	 * @param context
	 */
	public EchoHandler(ServerContextImpl context) {
		super(context, "Echo request");
	}

	/* (non-Javadoc)
	 * @see org.epics.ca.impl.remote.AbstractResponseHandler#handleResponse(java.net.InetSocketAddress, org.epics.ca.core.Transport, byte, byte, int, java.nio.ByteBuffer)
	 */
	@Override
	public void handleResponse(final InetSocketAddress responseFrom, Transport transport, byte version, byte command, int payloadSize, final ByteBuffer payloadBuffer) {
		super.handleResponse(responseFrom, transport, version, command, payloadSize, payloadBuffer);

		// send back
		transport.enqueueSendRequest(
				new TransportSender() {

					@Override
					public void send(ByteBuffer buffer, TransportSendControl control) {
						// all at once...
						/*
						control.startMessage((byte)2, payloadBuffer.remaining());		/// TODO this is wrooooooong !!!!
						buffer.put(payloadBuffer);
						*/
						control.startMessage((byte)2, 0);
						control.setRecipient(responseFrom);
					}

					@Override
					public void lock() {
						// noop
					}

					@Override
					public void unlock() {
						// noop
					}
					
			});
	}

}
