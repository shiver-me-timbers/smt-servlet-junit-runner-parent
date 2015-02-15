/*
 * Copyright (C) 2015  Karl Bennett
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package shiver.me.timbers.junit.runner.servlet;

import org.junit.Test;
import org.junit.runner.notification.RunListener;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ShutdownRunListenerFactoryTest {

    @Test
    public void A_shutdown_run_listener_is_created() throws Exception {

        // Given
        final Container container = mock(Container.class);

        // When
        final RunListener listener = new ShutdownRunListenerFactory().create(container);
        listener.testRunFinished(null);

        // Then
        verify(container).shutdown();
    }
}
