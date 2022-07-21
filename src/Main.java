public class Main {
    public static void main(String[] args) {
        String[] indexes = new String[]{"1,3-5", "2", "3-4"};
        Port port = new Port(indexes);
        System.out.println(port.getNumArray());
    }
}
