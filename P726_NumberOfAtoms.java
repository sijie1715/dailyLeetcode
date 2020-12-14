package Solutions;

import java.util.*;

public class P726_NumberOfAtoms {
    public String countOfAtoms(String formula) {
        Stack<Map<String, Integer>> stack = new Stack<>();
        Map<String, Integer> map = new HashMap<>();
        int i = 0;
        while (i < formula.length()) {
            char c = formula.charAt(i++);
            if (c == '(') {
                stack.push(map);
                map = new HashMap<>();
            } else if (c == ')') {
                // get num after )
                // i is already at 1 after )
                int val = 0;
                while (i < formula.length() && Character.isDigit(formula.charAt(i))) {
                    val = val * 10 + formula.charAt(i++) - '0';
                }
                if (val == 0) val = 1;
                // multiply all atoms in the () and add to outside(if there is)
                if (!stack.isEmpty()) {
                    Map<String, Integer> curMap = map;
                    map = stack.pop();
                    for (String key : curMap.keySet()) {
                        map.put(key, map.getOrDefault(key, 0) + curMap.get(key) * val);
                    }
                }
            }
            // case of atoms
            else {
                // i is already 1 ahead. Get full atom name
                int letterPos = i - 1;
                while (i < formula.length() && Character.isLowerCase(formula.charAt(i))) {
                    i++;
                }
                String atomName = formula.substring(letterPos, i);
                // get number after name
                int val = 0;
                while (i < formula.length() && Character.isDigit(formula.charAt(i))) {
                    val = val * 10 + formula.charAt(i++) - '0';
                }
                if (val == 0) val = 1;
                map.put(atomName, map.getOrDefault(atomName, 0) + val);
            }
        }
        // stack should be empty
        StringBuilder sb = new StringBuilder();
        List<String> atomNames = new ArrayList<>(map.keySet());
        Collections.sort(atomNames);
        for (String atomName : atomNames) {
            sb.append(atomName);
            if (map.get(atomName) > 1) sb.append(map.get(atomName));
        }
        return sb.toString();
    }
}
