package hua.lee.puzzle;

import java.util.Set;

/**
 * 目前还未看懂 Java并发编程在 151 页的示例：谜题框架
 * @param <P>
 * @param <M>
 */
public interface Puzzle<P, M> {
    P initialPosition();
    boolean isGoal(P position);
    Set<M> legalMoves(P position);
    P move(P position,M move);
}