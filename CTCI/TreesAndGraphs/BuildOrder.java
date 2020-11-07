package CTCI.TreesAndGraphs;

import CTCI.ArraysAndStrings.StringUtils;
import DataStructures.StackG;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// you're given a list of projects and a list of dependencies (which is a list of pairs of projects, where the second
// project is dependent on the first project). All of project's dependencies must be built before the project is.
// Find a build order that will allow the projects to be built. IF there is no valid build order, return an error.
public class BuildOrder {
    public static void main(String[] args) {
        testCase(new String[]{"a", "b", "c", "d", "e", "f"},
                new String[][]{{"a", "d"}, {"f", "b"}, {"b", "d"}, {"f", "a"}, {"d", "c"}}, false);

        testCase(new String[]{"a", "b", "c", "d", "e", "f"},
                new String[][]{{"a", "d"}, {"f", "b"}, {"b", "d"}, {"f", "a"}, {"d", "c"}}, true);

        testCase(new String[]{"a", "b", "c", "d", "e", "f", "g", "h"},
                new String[][]{{"f", "c"}, {"f", "b"}, {"f", "a"}, {"c", "a"}, {"b", "a"}, {"a", "e"}, {"b", "e"}, {"d", "g"}}, false);

        testCase(new String[]{"a", "b", "c", "d", "e", "f", "g", "h"},
                new String[][]{{"f", "c"}, {"f", "b"}, {"f", "a"}, {"c", "a"}, {"b", "a"}, {"a", "e"}, {"b", "e"}, {"d", "g"}}, true);
    }

    private static void testCase(String[] projects, String[][] dependencies, boolean doDfs) {
        System.out.println("[[BuildOrder" + (doDfs ? "DFS" : "") + ")]]Finding out build order for the following projects:");
        StringUtils.print(projects);
        System.out.println();

        System.out.println("Having dependencies: ");
        StringUtils.print(dependencies);

        Project[] buildOrder = calculateBuildOrder(projects, dependencies);
        if (buildOrder == null) {
            System.out.print("ERROR: couldn't find possible build order.");
        } else {
            System.out.print("Here's your build order");
            print(buildOrder);
        }

    }

    static void print(Project[] projects) {
        System.out.println();
        for (Project project : projects) {
            System.out.print(project.getName() + "\t");
        }
        System.out.println();
    }

    // time complexity o(p + d) where p is the number of projects and d the number of dependency pairs
    // space complexity o(p + d) -- p = vertices and d = edges
    static Project[] calculateBuildOrder(String[] projects, String[][] dependencies) {
        Graph graph = buildGraph(projects, dependencies);
        return calculateBuildOrder(graph.getNodes());
    }

    static Project[] calculateBuildOrderDFS(String[] projects, String[][] dependencies) {
        Graph graph = buildGraph(projects, dependencies);
        return calculateBuildOrderDFS(graph.getNodes());
    }

    private static Project[] calculateBuildOrderDFS(List<Project> projects) {
        StackG<Project> reverseBuildOrder = new StackG<>();
        for (Project project : projects) {
            if (!doDfs(project, reverseBuildOrder)) {
                return null;
            }
        }

        Project[] buildOrder = new Project[projects.size()];
        int index = 0;
        while (!reverseBuildOrder.isEmpty()) {
            Project current = reverseBuildOrder.pop();
            buildOrder[index] = current;
            index++;
        }
        return buildOrder;
    }

    private static boolean doDfs(Project project, StackG<Project> reverseBuildOrder) {
        if (project.getState() == ProjectState.PROCESSING) {
            return false;
        }

        if (project.getState() == ProjectState.READY_FOR_PROCESSING) {
            project.setState(ProjectState.PROCESSING);
            List<Project> dependencies = project.getDependencies();
            for (Project dependency : dependencies) {
                if (!doDfs(dependency, reverseBuildOrder)) {
                    return false;
                }
            }

            project.setState(ProjectState.BUILT);
            reverseBuildOrder.push(project);
        }

        return true;
    }

    private static Project[] calculateBuildOrder(List<Project> projects) {
        int projectsSize = projects.size();
        Project[] buildOrder = new Project[projectsSize];
        int endOfList = addNonDependentProjects(buildOrder, projects, 0);

        // loop through all the projects
        int toProcess = 0;
        while (toProcess < projectsSize) {
            Project project = buildOrder[toProcess];
            if (project == null) {
                return null;
            }

            List<Project> dependencies = project.getDependencies();
            for (Project dependency : dependencies) {
                dependency.decrementDependenciesCount();
            }

            endOfList = addNonDependentProjects(buildOrder, dependencies, endOfList);
            toProcess++;
        }

        return buildOrder;
    }

    private static int addNonDependentProjects(Project[] buildOrder, List<Project> nodes, int index) {
        for (Project node : nodes) {
            if (node.getDependenciesCount() == 0) {
                buildOrder[index] = node;
                index++;
            }
        }

        return index;
    }

    private static Graph buildGraph(String[] projects, String[][] dependencies) {
        Graph graph = new Graph();
        for (String project : projects) {
            graph.addProject(project);
        }

        for (String[] dependency : dependencies) {
            String dependee = dependency[0];
            String dependent = dependency[1];
            graph.connect(dependee, dependent);
        }

        return graph;
    }
}

class Graph {
    private List<Project> _nodes = new java.util.ArrayList<>();
    private Map<String, Project> projectMap = new HashMap<>();

    public void connect(String from, String to) {
        Project fromNode = getOrCreateNode(from);
        Project toNode = getOrCreateNode(to);
        fromNode.addDependency(toNode);
    }


    private Project getOrCreateNode(String name) {
        if (projectMap.containsKey(name)) {
            return projectMap.get(name);
        }

        Project project = new Project(name);
        projectMap.put(name, project);
        return project;
    }

    public List<Project> getNodes() {
        return _nodes;
    }

    void addProject(String project) {
        Project projectNode = getOrCreateNode(project);
        _nodes.add(projectNode);
    }
}

class Project {
    private final String _name;
    private Map<String, Project> _dependencyMap = new HashMap<>(); // projects that are dependent on this
    private List<Project> _dependencies = new ArrayList<>(); // projects that are dependent on this
    private int _dependenciesCount; // project depends on this projects

    private ProjectState _state;

    Project(String name) {
        _name = name;
    }

    List<Project> getDependencies() {
        return _dependencies;
    }

    void addDependency(Project dependency) {
        String dependencyName = dependency.getName();
        if (!_dependencyMap.containsKey(dependencyName)) {
            _dependencyMap.put(dependencyName, dependency);
            _dependencies.add(dependency);
            dependency.incrementDependencies();
        }
    }

    private void incrementDependencies() {
        _dependenciesCount++;
    }

    public String getName() {
        return _name;
    }

    public int getDependenciesCount() {
        return _dependenciesCount;
    }

    public void setDependenciesCount(int _dependenciesCount) {
        this._dependenciesCount = _dependenciesCount;
    }

    public void decrementDependenciesCount() {
        _dependenciesCount--;
    }

    public ProjectState getState() {
        return _state;
    }

    public void setState(ProjectState _state) {
        this._state = _state;
    }
}

enum ProjectState {
    READY_FOR_PROCESSING,
    BUILT,
    PROCESSING
}