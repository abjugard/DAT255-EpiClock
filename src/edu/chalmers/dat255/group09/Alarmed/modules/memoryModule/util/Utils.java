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
package edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.util;

import edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.model.Card;

/**
 * 
 * @author Joakim Persson
 * 
 */
public class Utils {

	private Utils() {
	}

	/**
	 * Get a copy of a matrix representing the gameboard used in the game
	 * 
	 * @param originalBoard
	 *            The tile matrix map
	 * @return A copy of the map
	 */
	public static Card[][] copyGameBoard(Card[][] originalBoard) {

		if (!isValidBoard(originalBoard)) {
			throw new IllegalArgumentException("Use A Correct GameBoard!");
		}

		int height = originalBoard.length;
		int width = originalBoard[0].length;

		Card[][] copyOfBoard = new Card[height][width];

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				copyOfBoard[i][j] = originalBoard[i][j];
			}
		}
		return copyOfBoard;
	}

	/**
	 * Performs validation of the orginal gameboard and makes sure that it
	 * matches the criterias to be a GameBoard. The map must be a square
	 * 
	 * @param originalBoard
	 *            The GameBoard to be copied
	 * @return A copy of the GameBoard
	 */
	private static boolean isValidBoard(Card[][] originalBoard) {

		int width = originalBoard[0].length;
		int height = originalBoard.length;

		if (width != height) {
			return false;
		}

		for (int i = 1; i < height; i++) {
			if (width != originalBoard[i].length) {
				return false;
			} else {
				width = originalBoard[i].length;
			}
		}
		return true;
	}
}
