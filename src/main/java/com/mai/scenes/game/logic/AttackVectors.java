package com.mai.scenes.game.logic;

import com.mai.datastructs.Stapel;


public record AttackVectors(Stapel<Space> possibleOneRangeAttackVectors, Stapel<Space> possibleTwoRangeAttackVectors) {
}
