import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Port {
    private String[] indexes;

    public Port(String[] indexes) {
        this.indexes = indexes;
    }

    public Integer[][] getNumArray() {
        List<Integer[]> inputIndexes = new ArrayList<>();
        for (String index : indexes) {
            Integer[] line = convertLine(index);
            inputIndexes.add(line);
        }
        return convert(inputIndexes).toArray(Integer[][]::new);
    }

    private List<Integer[]> convert(List<Integer[]> input) {
        List<Integer[]> output = new ArrayList<>();
        if (input.size() == 1) {
            output.add(input.get(0));
        } else {
            for (Integer num : input.get(0)) {
                List<Integer[]> integers = input.subList(1, input.size());
                output.addAll(reduce(num, integers.toArray(Integer[][]::new)));
            }
        }
        return output;
    }

    private List<Integer[]> reduce(Integer num, Integer[]... numbers) {
        List<Integer[]> result = new ArrayList<>();
        if (numbers.length == 1) {
            for (Integer number : numbers[0]) {
                Integer[] integers = new Integer[2];
                integers[0] = num;
                integers[1] = number;
                result.add(integers);
            }
        } else {
            for (Integer number : numbers[0]) {
                result.addAll(reduce(number, numbers[1]));
            }
        }
        return result;
    }

    private Integer[] convertLine(String line) {
        String[] splitLine = line.trim().split(",");
        List<Integer> result = new ArrayList<>();
        for (String block : splitLine) {
            if (block.contains("-")) {
                result.addAll(Arrays.asList(convertBlock(block)));
            } else if (block.matches("\\d+")) {
                result.add(Integer.parseInt(block));
            }
        }
        Collections.sort(result);
        return result.toArray(Integer[]::new);
    }

    private Integer[] convertBlock(String block) {
        List<Integer> result = new ArrayList<>();
        String[] splitBlock = block.trim().split("-");
        int[] num = Arrays.stream(splitBlock).mapToInt(Integer::parseInt).toArray();
        int minValue = Arrays.stream(num).min().orElse(num[0]);
        int maxValue = Arrays.stream(num).max().orElse(num[num.length - 1]);
        for (int i = minValue; i <= maxValue; i++) {
            result.add(i);
        }
        return result.toArray(Integer[]::new);
    }

    public String[] getIndexes() {
        return indexes;
    }

    public void setIndexes(String[] indexes) {
        this.indexes = indexes;
    }
}
