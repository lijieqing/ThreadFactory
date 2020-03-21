package hua.lee.puzzle;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;

import hua.lee.puzzle.SequentialPuzzleSolver.Node;

public class ConcurrentPuzzleSolver<P, M> {
    private final Puzzle<P,M> puzzle;
    private final ExecutorService exec;
    private final ConcurrentMap<P,Boolean> seen;
    final ValueLatch<Node<P,M>> solution = new ValueLatch<>();
    public ConcurrentPuzzleSolver(Puzzle<P,M> puzzle,ExecutorService exec,ConcurrentMap<P,Boolean> seen){
        this.puzzle = puzzle;
        this.exec = exec;
        this.seen = seen;
    }
    public List<M> solve() throws InterruptedException {
        try {
            P p = puzzle.initialPosition();
            exec.execute(newTask(p, null, null));
            Node<P,M> solNode = solution.getValue();
            return (solNode==null) ? null:solNode.asMoveList();
        } finally {
            exec.shutdown();
        }

    }
    protected Runnable newTask(P p,M m,Node<P,M> n){
        return new ResolverTask(p, m, n);
    }

    class ResolverTask extends Node<P,M> implements Runnable{

        ResolverTask(P pos, M move, Node<P, M> prev) {
            super(pos, move, prev);
        }

        @Override
        public void run() {
            if(solution.isSet() || seen.putIfAbsent(pos, true) != null){
                return;
            }
            if(puzzle.isGoal(pos)){
                solution.setValue(this);
            }else{
                for (M m : puzzle.legalMoves(pos)) {
                    exec.execute(newTask(puzzle.move(pos, m), m, this));
                }
            }

        }
        
    }
}