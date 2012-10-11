/*
 * Copyright (C) 2012 Joakim Persson, Daniel Augurell, Adrian Bjugard, Andreas Rolen
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
package edu.chalmers.dat255.group09.Alarmed.test.modules.memoryModule.util;

import java.util.Arrays;

import android.test.AndroidTestCase;
import edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.model.Card;
import edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.util.Utils;

/**
 * 
 * @author Joakim Persson
 * 
 */
public class UtilTest extends AndroidTestCase {

	public void testCopyGameBoard() {
		Card stubCard = new Card(0);
		Card[][] gameBoard = { { stubCard, stubCard, stubCard },
				{ stubCard, stubCard, stubCard },
				{ stubCard, stubCard, stubCard } };

		Card[][] boardCopy = Utils.copyGameBoard(gameBoard);
		assertTrue(Arrays.deepEquals(gameBoard, boardCopy));

	}

	public void testCopyIllegalGameBoard() {
		Card stubCard = new Card(0);
		Card[][] gameBoard = { { stubCard, stubCard, stubCard },
				{ stubCard, stubCard, stubCard },
				{ stubCard, stubCard, stubCard, stubCard } };
		try {
			Utils.copyGameBoard(gameBoard);
			fail("Should generate an IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	public void testCopyRectangleGameBoard() {
		Card stubCard = new Card(0);
		Card[][] gameBoard = { { stubCard, stubCard, stubCard },
				{ stubCard, stubCard, stubCard },
				{ stubCard, stubCard, stubCard },
				{ stubCard, stubCard, stubCard } };

		try {
			Utils.copyGameBoard(gameBoard);
			fail("Should generate an IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}

	}
}