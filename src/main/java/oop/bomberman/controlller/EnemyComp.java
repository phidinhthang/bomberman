package oop.bomberman.controlller;

import oop.bomberman.animator.EnemyAnimator;
import oop.bomberman.entities.Movable;

public class EnemyComp {
	private Movable entity;
	private EnemyMover mover;
	private EnemyAnimator animator;

	public Movable getEntity() {
		return entity;
	}

	public EnemyMover getMover() {
		return mover;
	}

	public EnemyAnimator getAnimator() {
		return animator;
	}

	public EnemyComp(
		Movable entity,
		EnemyMover mover,
		EnemyAnimator animator
	) {
		this.entity = entity;
		this.mover = mover;
		this.animator = animator;
	}	
}
