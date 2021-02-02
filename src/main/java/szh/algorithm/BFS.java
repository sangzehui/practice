package szh.algorithm;

import szh.leetcode.Solution1611;

import java.util.*;

public class BFS {
    class Stage {
        Object value;
        Map<String, Object> map = new HashMap<>();

        public Stage(Object object) {
            value = object;
        }

        public List<Stage> nextStages() {
            List<Stage> list = new ArrayList<>();
            return list;
        }

        public boolean isFinished() {
            return false;
        }
    }

    public Stage bfs(Object obj) {
        LinkedList<Stage> stack = new LinkedList<>();
        stack.add(new Stage(obj));
        while (!stack.isEmpty()) {
            Stage stage = stack.removeFirst();
            if (stage.isFinished()) {
                return stage;
            }
            stack.addAll(stage.nextStages());
        }
        return null;
    }
}
