import com.cheney.entity.vo.LatLonReq;
import com.cheney.test.MyException;
import com.cheney.test.Pair;
import com.cheney.test.TraceHandler;
import com.cheney.utils.ObjectAnalyzer;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

/**
 * @author linch
 * @create 2021/12/7 10:14
 */
@Slf4j
@RunWith(SpringRunner.class)
//@SpringBootTest
public class JavaFirstTest {

    @Test
    public void testFive() throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {

        ArrayList<String> testInt = new ArrayList<>(100);
        testInt.add("one");
        testInt.add("two");
        log.info("数组大小 ---- " + testInt.size());

        testInt.trimToSize();
        log.info("数组 trimToSize 大小 ---- " + testInt.size());

        double max = max(1.2, 69.6, 23.3, 10000.393);
        log.info("max ---- " + max);
        String name = "java.util.Random";
        Class<?> aClass = Class.forName(name);
        Object o = aClass.getConstructor(long.class).newInstance(1L);
        String s = o.toString();
        log.info("s ---- " + s);
        log.info("o ---- " + o);

    }

    @Test
    public void testToString() throws ClassNotFoundException {
        ArrayList<LatLonReq> squares = new ArrayList<>();
        for (int i = 1; i <= 5; i++)
            squares.add(new LatLonReq(1.0 + i,2.0 + i));
        log.info("--------------------------------start -------------------------");
        log.info(new ObjectAnalyzer().toString(squares));
        log.info("--------------------------------log -------------------------");
        //java.util.ArrayList[elementData=class java.lang.Object[]
        // {com.cheney.entity.vo.LatLonReq[latitude=2.0,longitude=3.0,radius=0.0][],
        // com.cheney.entity.vo.LatLonReq[latitude=3.0,longitude=4.0,radius=0.0][],
        // com.cheney.entity.vo.LatLonReq[latitude=4.0,longitude=5.0,radius=0.0][],
        // com.cheney.entity.vo.LatLonReq[latitude=5.0,longitude=6.0,radius=0.0][],
        // com.cheney.entity.vo.LatLonReq[latitude=6.0,longitude=7.0,radius=0.0][],
        // null,null,null,null,null},size=5][modCount=5][][]
        log.info("--------------------------------start -------------------------");
        log.info(new ObjectAnalyzer().toString(new LatLonReq(123,234)));
        log.info("--------------------------------log -------------------------");

        String[] b = { "Tom", "Dick", "Harry" };
        b = (String[]) ObjectAnalyzer.goodCopyOf(b, 10);
        System.out.println(Arrays.toString(b));

        String text = "wa String";
        Class<?> aClass = Class.forName("java.lang.String");
        Class<? extends String> aClass1 = text.getClass();
        String r = aClass.getComponentType() + "[]{";
        log.info("r ------------ " + r);
        Class<? extends String[]> aClass2 = b.getClass();
        String s = aClass2.getComponentType() + "";
        log.info("s ----------- " + s);
        Class<? extends ArrayList> aClass3 = squares.getClass();
        String dd = aClass3.getComponentType() + ";dfdfd;";
        log.info("dd ---------- " + dd);
    }

    @Test
    public void cloneTest() throws CloneNotSupportedException {
        repeat(10, () -> System.out.println("hello"));
    }

    private static double max(double...values){
        double largest = Double.NEGATIVE_INFINITY;
        log.info("largest ---- " + largest);
        for(double v : values) {
            if(v > largest) {
                largest = v;
            }
        }
        return largest;
    }

    public static void repeat(int n, Runnable runnable){
        for(int i = 0;i < n; i++) runnable.run();
    }

    @Test
    public void testInnerClass(){
        List<String> list = new ArrayList<String>(){{add("1000");add("3434");}};
        log.info(Arrays.toString(list.toArray()));
    }

    @Test
    public void invocationTest(){

        Object[] elements = new Object[1000];

        // fill elements with proxies for the integers 1 ... 1000
        for (int i = 0; i < elements.length; i++)
        {
            Integer value = i + 1;
            InvocationHandler handler = new TraceHandler(value);
            Object proxy = Proxy.newProxyInstance(null, new Class[] { Comparable.class } , handler);
            elements[i] = proxy;
        }

        // construct a random integer
        Integer key = new Random().nextInt(elements.length) + 1;

        // search for the key
        int result = Arrays.binarySearch(elements, key);

        // print match if found
        if (result >= 0) System.out.println(elements[result]);
//        for (int i = 0; i < elements.length; i++){
//            System.out.println(elements[i]);
//        }

    }

    @Test
    public void testMyException() {
        try {
            testException();
        } catch (MyException | FileNotFoundException e) {
            e.printStackTrace();
            Throwable cause = e.getCause();
            log.info("打印原本的异常，这里应该是filenotfondexception");
            cause.printStackTrace();
            StackTraceElement[] stackTrace = e.getStackTrace();
            for (int i = 0; i < stackTrace.length; i++) {
                StackTraceElement stackTraceElement = stackTrace[i];
                String className = stackTraceElement.getClassName();
                int lineNumber = stackTraceElement.getLineNumber();
                String fileName = stackTraceElement.getFileName();
                String methodName = stackTraceElement.getMethodName();
                Class<? extends StackTraceElement> aClass = stackTraceElement.getClass();
                boolean nativeMethod = stackTraceElement.isNativeMethod();
                String s = stackTraceElement.toString();
                log.info("className ----- " + className);
                log.info("lineNumber ---- " + lineNumber);
                log.info("fileName ------ " + fileName);
                log.info("methodName -------" + methodName);
                log.info("s ------ " + s);
                log.info("nativeMethod --------- " + nativeMethod);
                log.info("aClass ------- " + aClass.getName());
                log.info("aClass ------ " + aClass.getTypeName());
            }
        }
    }

    @Test
    public void testAssert(){
        int i = 0;
        assert i == 0: i +=1;
        log.info("assert");

        log.info("log ---getName-- " + log.getName());
        log.info("log ---getClass-- " + log.getClass());
        log.info("log ---getClass--getName " + log.getClass().getName());

        Logger javaFirstTest = Logger.getLogger("JavaFirstTest");
        javaFirstTest.info("test log");
        String name = javaFirstTest.getName();
        Class<? extends Logger> aClass = javaFirstTest.getClass();
        String name1 = javaFirstTest.getClass().getName();
        javaFirstTest.info("name ---- "+ name);
        javaFirstTest.info("name1 ---- "+ name1);
        javaFirstTest.info("aClass ---- "+ aClass);
    }

    public void testException() throws MyException, FileNotFoundException {
        MyException e = new MyException("myException Error");

        String message = e.getMessage();
        log.info("日志 --- message --- " + message);
        int i = 1;
        if(i == 2){
            throw e;
        } else {
            FileNotFoundException fe = new FileNotFoundException();
            MyException se = new MyException("捕获 包装 重新抛出 Error");
            se.initCause(fe);
            throw se;
        }
    }

    public int factorial(int n)
    {
        System.out.println("factorial(" + n + "):");
        Throwable t = new Throwable();
        StackTraceElement[] frames = t.getStackTrace();
        for (StackTraceElement f : frames)
            System.out.println(f);
        int r;
        if (n <= 1) r = 1;
        else r = n * factorial(n - 1);
        System.out.println("return " + r);
        return r;
    }

    @Test
    public void testFactorial()
    {
        factorial(3);
    }

    @Test
    public void testAssertOrNot(){
        try{
            testNotAssert(0);
        }catch (Exception e){
            log.info("i 大于 0 进入输入");
            Logger.getGlobal().info("Logger   -----    i 大于 0 进入输入");
        }


    }

    private void testAssert(int i){
        assert i != 0;
        int j = 10 / i;
        Scanner sc = new Scanner(System.in);
        log.info("i 大于 0 进入输入" + j);
    }

    private void testNotAssert(int i){
        int j = 10 / i;
        Scanner sc = new Scanner(System.in);
        log.info("i 大于 0 进入输入" + j);
    }

    @Test
    public void testLog(){
        Logger logger = Logger.getLogger ("com.cheney.test.MyException", "com.cheney.test.logmessages_zh" );
        logger.info("readingFile");
        logger.info("renamingFile");
    }

    @Test
    public void testT(){
        Pair<String>[] pairs = (Pair<String>[]) new Pair<?>[10];
        pairs[1] = new Pair<>("2","2");
        pairs[0] = new Pair<>("dfdfdf","dfdfdfd");
        Object[] objects = pairs;
        objects[1] = new Pair<>(new LatLonReq(1,1), new LatLonReq(1,1));
        log.info(Arrays.toString(objects));
        String first = pairs[1].getFirst();
        log.info(first);


        Pair<String> pair1 = new Pair<>("1", "22");
        Pair<String> pair2 = new Pair<>("1fdfd", "你好");
        Pair<String>[] table = arrays(pair1, pair2);
        Object[] objarray = table;
        objarray[0] = new Pair<>(new LatLonReq(1.2,2.0), new LatLonReq(2.0,3.0));
        log.info(Arrays.toString(objarray));
        Pair<String> pair = table[0];
        log.info(pair.getFirst());
    }

    @SafeVarargs
    private static <E> E[] arrays(E...array){
        return array;
    }

    @Test
    public void testTTT(){
        ArrayList<List<Integer>> list = new ArrayList<>();

        for (int i = 0; i < 10; i++){
            List<Integer> list1 = new ArrayList<>();
            for (int j = 0; j < 20; j++){
                list1.add(j);
            }
            list.add(list1);
        }
        list.forEach(item -> {
            log.info("item ---- " + item);
            item.clear();
            log.info("item ---- " + item);
        });
    }

    private void testForLog(String j){
        for (int i =0; i< 20; i++){
            log.info("i -------- " + j + "-----" + i);
        }
    }



    private <T> T testTT(T t){
        if(t instanceof String){
            return (T) "你好";
        }
        if(t instanceof Double){
            return (T) Double.valueOf(2.0);
        }
        if(t instanceof LatLonReq){
            return (T) new LatLonReq(1.2, 2.2);
        }
        return (T) Double.valueOf(1.0);
    }

    @Test
    public void testCount(){
        Collection<LatLonReq> collection = new ArrayList<>();
        LatLonReq latLonReq = new LatLonReq(1.2, 2.0);
        LatLonReq latLonReq2 = new LatLonReq(1.2, 2.0);
        collection.add(latLonReq);
        collection.add(latLonReq2);
        log.info("i ---- " + collection.size());
        Object[] objects = collection.toArray();
        log.info("objects ----- " + Arrays.toString(objects));
        LatLonReq[] latLonReqs = collection.toArray(new LatLonReq[10]);
        log.info("lat ---------- " + Arrays.toString(latLonReqs));
        log.info("i ---- " + collection.size());
        log.info("toString --------- " + latLonReq2.toString());
    }

    @Test
    public void testLinkList(){
        List<String> a = new LinkedList<>();
        a.add("Amy");
        a.add("Carl");
        a.add("Erica");

        List<String> b = new LinkedList<>();
        b.add("Bob");
        b.add("Doug");
        b.add("Frances");
        b.add("Gloria");

        // merge the words from b into a

        ListIterator<String> aIter = a.listIterator();
        Iterator<String> bIter = b.iterator();

        while (bIter.hasNext())
        {
            if (aIter.hasNext()) aIter.next();
            aIter.add(bIter.next());
        }

        System.out.println(a);

        // remove every second word from b

        bIter = b.iterator();
        while (bIter.hasNext())
        {
            bIter.next(); // skip one element
            if (bIter.hasNext())
            {
                bIter.next(); // skip next element
                bIter.remove(); // remove that element
            }
        }

        System.out.println(b);

        // bulk operation: remove all words in b from a

        a.removeAll(b);

        System.out.println(a);
    }

    @Test
    public void testPriorityQueue(){
        PriorityQueue<LocalDate> pq = new PriorityQueue<>();
        pq.add(LocalDate.of(1906, 12, 9)); // G. Hopper
        pq.add(LocalDate.of(1815, 12, 10)); // A. Lovelace
        pq.add(LocalDate.of(1903, 12, 3)); // J. von Neumann
        pq.add(LocalDate.of(1910, 6, 22)); // K. Zuse

        System.out.println("Iterating over elements...");
        for (LocalDate date : pq)
            System.out.println(date);
        System.out.println("Removing elements...");
        while (!pq.isEmpty())
            System.out.println(pq.remove());
    }

    @Test
    public void testMap(){
        //测试hashMap
        Map<String, Integer> map = new HashMap();
        map.put(null, 10);
        map.put("2", 2);
        map.put(null,null);
        //如果存在则更新
        map.computeIfPresent("1", (k,v) -> v + 10);
        //更新 37700 12300
        map.merge("1", 2 , Integer::sum);
        //如果不存在则put
        map.putIfAbsent("2",10);
        map.putIfAbsent("2",12);
        //替换
        map.replace("2",12);
        log.info("map ---- " + map);
        Integer integer = map.get(null);
        log.info("integer --- " + integer);

        //测试并发hashmap
        ConcurrentHashMap<String, Integer> map1 = new ConcurrentHashMap();
        map1.put("1", 10);
        map1.putIfAbsent("1", 12);
        log.info("map1 --- " + map1);
        //测试并发hashmap
        ConcurrentHashMap<String, LongAdder> map2 = new ConcurrentHashMap();
        //new LongAdder默认0
        map2.putIfAbsent("2", new LongAdder());
        //如果直接使用，未执行上一步，会报值为null异常 java.lang.NullPointerException
        map2.putIfAbsent("2", new LongAdder()).increment();
        map2.computeIfPresent("2", (k,v) -> new LongAdder()).increment();
        map2.computeIfAbsent("3", k -> new LongAdder()).increment();
        log.info("map2 --- " + map2);

    }

    @Test
    public void testMapSet(){
        Map<String, String> map = new HashMap();
        map.put("adam","adam - value");
        map.put("boolean","boolean - value");
        map.put("chen","chen - value");

        Set<String> strings = map.keySet();
        Iterator<String> iterator = strings.iterator();
//        for(String s : strings){
//            if(s.equals("adam")){
////                strings.remove(s);
//            }
//        }
        while (iterator.hasNext()){
            String next = iterator.next();
            if(next.equals("adam")){
                iterator.remove();
                break;
            }
        }
        log.info("keySet ---- " + strings);

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        Iterator<String> iterator1 = list.iterator();
        while (iterator1.hasNext()){
            iterator1.next();
            iterator1.remove();
            break;
        }
        log.info("list --- " + list);

    }

    @Test
    public void mapLinkedTest(){
        Map<String, String> staff = new LinkedHashMap<>();

        staff.put("144-25-5464", "Amy Lee");
        staff.put("567-24-2546", "Harry Hacker");
        staff.put("157-62-7935", "Gary Cooper");
        staff.put("456-62-5527", "Francesca Cruz");

        Iterator<String> iterator = staff.keySet().iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            log.info("key next ---- " + next);
        }

        Iterator<String> iterator1 = staff.values().iterator();
        while (iterator1.hasNext()){
            String next = iterator1.next();
            log.info("value next ---- " + next);
        }


    }

    @Test
    public void mapTest(){
        Map<String, String> staff = new HashMap<>();
        staff.put("144-25-5464", "Amy Lee");
        staff.put("567-24-2546", "Harry Hacker");
        staff.put("157-62-7935", "Gary Cooper");
        staff.put("456-62-5527", "Francesca Cruz");

        Iterator<String> iterator = staff.keySet().iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            log.info("key next ---- " + next);
        }

        Iterator<String> iterator1 = staff.values().iterator();
        while (iterator1.hasNext()){
            String next = iterator1.next();
            log.info("value next ---- " + next);
        }
    }

    @Test
    public void testLinkedHashMap(){
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>(4, 0.75F,true);
        linkedHashMap.put("144-25-5464", "Amy Lee");
        linkedHashMap.put("567-24-2546", "Harry Hacker");
        linkedHashMap.put("157-62-7935", "Gary Cooper");
        linkedHashMap.put("456-62-5527", "Francesca Cruz");

        log.info("linkedHashMap --- " + linkedHashMap);
        linkedHashMap.forEach((k,v) -> log.info("k --- " + k + " --- v ----" + v));
        String s = linkedHashMap.get("567-24-2546");
        log.info("s --- " + s);
        log.info("linkedHashMap --- " + linkedHashMap);
        linkedHashMap.forEach((k,v) -> log.info("k --- " + k + " --- v ----" + v));
    }

    @Test
    public void testUnChange(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        List<String> list1 = list.subList(2, 3);
        list1.clear();
        log.info("list --- " +list);
        log.info("list1 --- " + list1);
    }

    @Test
    public void testCheck(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        List<String> list1 = Collections.checkedList(list, String.class);
        List integerList = list1;
        integerList.add(1);
        log.info("list --- " +integerList);
    }

    @Test
    public void testLinkedHashMapEldest(){
            Map<String, String> map = new LinkedHashMap<String, String>(4, 0.75F, true){
                @Override
                protected boolean removeEldestEntry(Map.Entry eldest) {
                    log.info("removeEldestEntry ----- " + eldest.getKey() + "---------" + eldest.getValue() + "class -----" + eldest.getClass().getName());
                    return size() > 2;
                }
            };
            map.put("a", "avalue");
            map.put("b", "bvalue");
            map.put("c", "cvalue");
            map.put("d", "dvalue");
            map.put("e", "evalue");
            map.put("f", "fvalue");
            log.info("map --- "+ map);

            List<String> list = new ArrayList<>();
            list.add("1;");
        list.add("1;");
        list.add("1;");
        list.add("1;");
        list.add("2;");
        list.add("1;");
        log.info("list---" + list);
//        List<Integer> integerList = Arrays.asList(1, 2, 3);
//        List<Integer> integerList1 = integerList.subList(0, 2);
//        Iterator<Integer> iterator1 = integerList1.iterator();
//        while (iterator1.hasNext()){
//            Integer next = iterator1.next();
//            iterator1.remove();
//        }
        List<String> list1 = list.subList(0, 2);
        log.info("list1 ---- "+ list1);
        Iterator<String> iterator = list1.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            iterator.remove();
        }
        log.info("list1 ---- "+ list1);

    }

    @Test
    public void testSort(){

        List<Integer> list = new ArrayList<>();
        list.add(55);
        list.add(15);
        list.add(35);
        list.add(25);
        list.add(5);
        Collections.sort(list);
        int i = Collections.binarySearch(list, 45);
        log.info("i ---- " + i);
        if(i < 0)
            list.add(-i -1 , 45);
        log.info("list --- " + list);
    }

    @Test
    public void testShuffle(){
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 49; i++)
            numbers.add(i);
        Collections.shuffle(numbers);
        List<Integer> winningCombination = numbers.subList(0, 6);
        Collections.sort(winningCombination);
        System.out.println(winningCombination);

    }

    @Test
    public void testInterrupt() throws InterruptedException{
        Runnable r = new MyRunable();
        Thread thread = new Thread(r);
        thread.start();
        thread.interrupt();


    }

    class MyRunable implements Runnable{
        @SneakyThrows
        @Override
        public void run() {
            for(int i = 0; i < 100000; i++){
                log.info("i ----- " + i);
                if(i == 10){
                    Thread.sleep(10000);
                }
            }
        }
    }

    @Test
    public void testThreadJoin() throws InterruptedException {


    }

    @Test
    public void testHandler() throws InterruptedException {
        Runnable r = () -> {
            for(int i = 0; i < 1000; i++){
                log.info("i ----- " + i);
                if(i == 10){
//                    try {
//                        Thread.sleep(5000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                   int j = i / 0;
                }
            }
        };
        MyThreadUnCatchHandler myThreadUnCatchHandler = new MyThreadUnCatchHandler();

        Thread current = Thread.currentThread();
        String name = Thread.currentThread().getName();
        log.info("currentThread name --- " + name);
        Thread rThread = new Thread(r);
//        rThread.setUncaughtExceptionHandler(myThreadUnCatchHandler);

        log.info("rThread name start ---- " + rThread.getName());
        log.info("current thread name  ----- " + current.getName());

        rThread.start();
//        rThread.interrupt();
//        rThread.join( 1000);
        log.info("current thread join log ---- " + current.getName());

    }

    static class MyThreadUnCatchHandler implements Thread.UncaughtExceptionHandler{

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            StackTraceElement[] stackTrace = t.getStackTrace();
            log.info("print exception log ----- " + Arrays.toString(stackTrace));
        }
    }


    public static void main(String[] args) {
//        double[] accounts = {1000.0,  1000.0, 1000.0, 1000.0, 1000.0, 1000.0, 1000.0, 1000.0, 1000.0, 1000.0};
//        BankTest bank = new BankTest(accounts);
//
//        for (int i = 0; i < NACCOUNTS; i++) {
//            int fromAccount = i;
//            Runnable r = () -> {
//                try {
//                    while (true) {
//                        int toAccount = (int) (bank.size() * Math.random());
//                        double amount = MAX_AMOUNT * Math.random();
//                        bank.transfer(fromAccount, toAccount, amount);
//                        Thread.sleep((int) (DELAY * Math.random()));
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            };
//            Thread thread = new Thread(r);
//            thread.start();
//        }
        Runnable r = () -> {
            for(int i = 0; i < 1000; i++){
                log.info("i ----- " + i);
                log.info("ddfdf" + i * 8598*52662/88228);
                if(i == 10){
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
//                        log.info("catch 中断异常");
//                        e.printStackTrace();
//                        log.info("线程中断异常");
//                        Thread.currentThread().interrupt();
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
                boolean interrupted = Thread.currentThread().isInterrupted();
                log.info("中断状态 ---- " + interrupted);
            }
        };
        Thread thread = new Thread(r);
        thread.start();
        thread.interrupt();
        log.info("线程中断异常");
    }

    public static final int NACCOUNTS = 100;
    public static final double INITIAL_BALANCE = 1000;
    public static final double MAX_AMOUNT = 1000;
    public static final int DELAY = 10;

    static class BankTest {
        private final double[] accounts;
        Lock bankLock = new ReentrantLock();

        BankTest(double[] accounts) {
            this.accounts = accounts;
        }

        public void transfer(int from, int to, double amount){
            bankLock.lock();
            try {
                log.info("current Thread ---- " + Thread.currentThread());
                accounts[from] -= amount;
                System.out.printf(" %10.2f from %d to %d", amount, from, to);
                accounts[to] += amount;
                System.out.printf("Total Balance : %10.2f%n", getTotalBalance());
            }finally {
                bankLock.unlock();
            }
        }

        public double getTotalBalance(){
            double result = Arrays.stream(accounts).sum();
            return result;
        }
        public int size(){
            return accounts.length;
        }
    }

    @Test
    public void testFinal(){
//        final LatLonReq latLonReq = new LatLonReq(2.0,2.0);
//        latLonReq.setRadius(1.0);
//        latLonReq = new LatLonReq(1.0,1.0);
//        final String test = "test";
//        test = "testChange";
    }

    @Test
    public synchronized void testLongAdder(){
        final AtomicLong atomicLong = new AtomicLong(20);
        long l = atomicLong.updateAndGet(x -> {
            log.info("x ---- " + x);
            return Math.max(x, 40L);
        });
        log.info("l ---- " + l);
        final LongAdder longAdder = new LongAdder();
        long l1 = longAdder.longValue();
        log.info("l1 ---- " + l1);
        longAdder.add(10L);
        longAdder.add(20l);
        longAdder.increment();
        long l2 = longAdder.longValue();
        log.info("l2 ---- " + l2);

        LongAccumulator longAccumulator = new LongAccumulator(Long::max, 10);
        long l3 = longAccumulator.get();
        longAccumulator.accumulate(20);
        long l4 = longAccumulator.get();
        log.info("l3 ---- " + l3);
        log.info("l4 ---- " + l4);
    }

    @Test
    public void testThreadLocal(){
        final ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));
//        String format = dateFormatThreadLocal.get().format(new Date());
//        log.info("format ---- " + format);
//
//        log.info("int 最大值 ---- " + Integer.MAX_VALUE);
        for (int i = 0 ; i < 10; i++) {
            Runnable r = () -> {
                String format = dateFormatThreadLocal.get().format(new Date());
                log.info("i ---------- " + Thread.currentThread().getName() + "-------dateFormatThreadLocal---- " + dateFormatThreadLocal.toString() + "format ---- " + format);
                dateFormatThreadLocal.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                format = dateFormatThreadLocal.get().format(new Date());
                log.info("i ---------- " + Thread.currentThread().getName() + "-------dateFormatThreadLocal---- " + dateFormatThreadLocal.toString() + "format ---- " + format);
            };
            Thread t = new Thread(r);
            t.start();
        }
    }

    @Test
    public void testMapObj(){
        HashMap<String, String> map = new HashMap<>();
        map.put("1","one");
        map.put("2","two");
        map.put("3","three");
        map.put("4","four");
        map.put("5","five");
        map.put("6","six");

        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        list.add("five");
        list.add("six");

        List<String> myList = list;
        myList.set(1, "我的");
        myList.set(2, "我们的");

        log.info("myList ------ " + myList);
        log.info("list ----------- " + list);



    }

    /**
     * 并发集合测试 原子操作
     */
    @Test
    public void testConcurrentHashMap(){
        //ERROR 不允许存在null值
/*        ConcurrentHashMap<String, LongAdder> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put(null,new LongAdder());
        log.info(concurrentHashMap + "----- " + concurrentHashMap.values());*/
        ConcurrentHashMap<String, LongAdder> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.compute("1",(k,v) -> v == null ? new LongAdder() : new LongAdder()) ;
        log.info(concurrentHashMap + "----- " + concurrentHashMap.values());
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put(null,1);
        log.info("hashMap -------- " + hashMap + "----------" + hashMap.values());
    }

    /**
     * 并发集合的批处理
     */
    @Test
    public void testConcurrentHashMapBatch(){

        //测试并发hashmap
        ConcurrentHashMap<String, Integer> map3 = new ConcurrentHashMap();
//        map3.compute("1" , (k,v) -> v == null ? 1 : v + 1 );

        log.info("map3" + map3);

        map3.put("2", 1000);
        map3.put("3", 100);
//        map3.computeIfPresent("1", (k,v) -> 99);

        map3.forEach(2, (k,v) -> v > 99 ? k + "->" + v : null, System.out::println);
        //因为查找第一个的时候，key是1，然后返回了字符串，那么就返回了，正确的用法，应该是 v > 99 ? k : null;
        String search1 = map3.search(2, (k, v) -> v > 99 ? k : "找不到");
        log.info("search1 ---- " + search1);//search ---- 找不到
        String search = map3.search(2, (k, v) -> v > 99 ? k : "找到了-----");//1000 ---> null
        log.info("search ---- " + search);//search ---- 2

        ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
        map.putIfAbsent("me", 1l);
        map.putIfAbsent("you", 2l);
        map.putIfAbsent("him", 3l);
        long sum = map.reduceValuesToLong (2 ,
                Long::longValue, 0 , Long :: sum ) ;
        log.info("sum -------- " + sum);

    }

    @Test
    public void testForeach(){
        List<String> list = new ArrayList<>();
        list.add("test1");
        list.add("stop");
        list.add("test2");
        list.add("last");
//        String s = list.stream().filter(item -> item.equals("")).findFirst().get();
        Optional<String> first = list.stream().filter(item -> item.equals("")).findFirst();
        String s = null;
        if(!first.isPresent()){
            return;
        }
        log.info("s --------------- " + s);
    }





}
