package LeetCode.medium;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseSchedule {
    public static void main(String[] args) {
        testCase(2, new int[][]{
                {1, 0}
        });

        testCase(2, new int[][]{
                {1, 0}, {0, 1}
        });

        testCase(20, new int[][]{
                {0, 10}, {3, 18}, {5, 5}, {6, 11}, {11, 14}, {13, 1}, {15, 1}, {17, 4}
        });
    }

    private static void testCase(int numCourses, int[][] courses) {
        LogUtils.logMessage("[[CourseSchedule]] Figuring if " + numCourses + " courses can be finished given the prerequisites matrix: ");
        ArrayUtils.printMatrix(courses);

        boolean canFinish = computeCanFinishCycle(numCourses, courses);
        LogUtils.logMessage("CanFinish: " + canFinish);
    }

    private static boolean computeCanFinishCycle(int numCourses, int[][] courses) {
        List<Integer>[] graph = buildGraph(numCourses, courses);
        boolean[] active = new boolean[numCourses];
        boolean[] visited = new boolean[numCourses];
        for (int vertex = 0; vertex < graph.length; vertex++) {
            if (hasCycle(vertex, graph, active, visited)) {
                return false;
            }
        }
        return true;
    }

    private static boolean hasCycle(int vertex, List<Integer>[] graph, boolean[] active, boolean[] visited) {
        if (!visited[vertex]) {
            active[vertex] = true;
            visited[vertex] = true;
            for (int adjacent : graph[vertex]) {
                if (!visited[adjacent] && hasCycle(adjacent, graph, active, visited)) {
                    return true;
                } else if (active[adjacent]) {
                    return true;
                }
            }
        }

        active[vertex] = false;
        return false;
    }

    private static List<Integer>[] buildGraph(int numCourses, int[][] courses) {
        List<Integer>[] graph = new List[numCourses];
        for (int x = 0; x < numCourses; x++) {
            graph[x] = new ArrayList<>();
        }

        for (int[] requisites : courses) {
            int dependent = requisites[0];
            int dependency = requisites[1];
            graph[dependent].add(dependency);
        }

        return graph;
    }

    // brute force...
    // loops through all courses until all dependencies are satisfied
    // time o(v*d) v = vertex and d = resolving dependencies cycles
    // space o(d) d = resolving dependencies cycles
    private static boolean computeCanFinish(int numCourses, int[][] courses) {
        boolean[] finished = new boolean[numCourses];
        return computeRecursively(numCourses, courses, finished);
    }

    private static boolean computeRecursively(int numCourses, int[][] courses, boolean[] finished) {
        if (computeFinished(finished) == numCourses) {
            return true;
        }

        List<Integer> toFinish = computeToFinish(numCourses, courses, finished);
        if (toFinish.isEmpty()) {
            return computeFinished(finished) == numCourses;
        }

        for (int course : toFinish) {
            finished[course] = true;
        }

        return computeRecursively(numCourses, courses, finished);
    }

    private static int computeFinished(boolean[] finished) {
        int coursesFinished = 0;
        for (int course = 0; course < finished.length; course++) {
            if (finished[course]) {
                coursesFinished++;
            }
        }
        return coursesFinished;
    }

    private static List<Integer> computeToFinish(int numCourses, int[][] courses, boolean[] finished) {
        Map<Integer, List<Integer>> dependenciesMap = new HashMap<>();
        for (int[] coursePair : courses) {
            int dependent = coursePair[0];
            int dependency = coursePair[1];
            List<Integer> dependencies = dependenciesMap.getOrDefault(dependent, new ArrayList<>());
            if (!finished[dependency]) {
                dependencies.add(dependency);
            }

            dependenciesMap.put(dependent, dependencies);
        }

        List<Integer> toFinish = new ArrayList<>();
        for (int course = 0; course < numCourses; course++) {
            if (finished[course]) {
                continue;
            }
            List<Integer> dependencies = dependenciesMap.getOrDefault(course, new ArrayList<>());
            if (dependencies.isEmpty()) {
                toFinish.add(course);
            }
        }

        return toFinish;
    }
}
