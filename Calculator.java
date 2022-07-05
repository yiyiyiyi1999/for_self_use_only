package com.atguigu.stack;
//自己写了一个简陋计算器！

public class Calculator {
    public static void main(String[] args) {
        String s =  "7*2*2-5+1-5+3-4";
        //创建两个栈
        Stack numStack = new Stack(10);
        Stack symStacks = new Stack(10);
        char[] ch = s.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] >= '0' && ch[i] <='9'){
                numStack.push((int)ch[i]-'0');
            } else {
                symStacks.calprio(ch[i], numStack, symStacks);
            }
        }
        int countnum = 0;
        int countsym = 0;
        int count = 0;
        while(true){
            count = count + numStack.cal(numStack.stack[countnum],symStacks.stack[countsym],numStack.stack[countnum + 1]);
            countnum++;
            countsym++;
            if (countsym > symStacks.top || countnum > numStack.top){
                break;
            }
        }
        System.out.println(count);


    }
}
class twoStack{


}
class Stack{
    int maxSize;
    int[] stack;
    int top = -1;
    //构造器
    public Stack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }
    //判断栈是不是满了，如果满了就返回true
    public boolean isFull(){
        return  top == maxSize - 1;
    }
    ///判断栈是不是空了
    public boolean isEmpty(){
        return top == -1;
    }
    //入栈-push
    public void push(int value) {
        //先判断是不是满了
        if(isFull()){
            return;
        }
        top++;
        stack[top] = value;
    }
    //出栈-pop, 将栈顶的数据返回
    public int pop() {
        if(isEmpty()){
            throw new RuntimeException("full");
        }
        int value = stack[top];
        top--;
        return value;

    }
    //显示栈的情况[遍历栈]， 遍历时，需要从栈顶开始显示数据
    public void list() {
        if(isEmpty()){
            System.out.println("empty");
        }
        for (int i = 0; i <= top; i++) {
            System.out.printf("stack[%d] = %d\n",i,stack[i]);
        }
    }
    //用于设置运算符的优先级
    public int setSymPri(char a){
        switch(a){
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case'^':
                return 3;
            default:
                return 4;
        }
    }
    //关于比较运算符优先级的方法
    public boolean comparePri(char a, char b){
        return setSymPri(a) - setSymPri(b) > 0;
        //a的优先级高于b的话就返回true；
    }
    //关于优先级小于等于就要pop
    public void calprio(char a, Stack num, Stack sym){
        int flag = 0;//这是新加入的符号优先级比较的标志，0表示优先级比里面的都大
        //加一个sym非空判断
        if(sym.top != -1){
            for (int i = 0; i < sym.top+1; i++) {
                if(comparePri(a,(char)sym.stack[top])){
                    flag = 1;
                    break;
                }
            }
        }else{
            //如果是空的，那才加到一个数字，所以直接sym的push然后结束就行
            sym.push(a);
            return;
        }

        switch (flag){
            case 1://那就是直接入符号栈
                sym.push(a);
                break;
            case 0://数pop两个符号，符号pop一个符号，计算后填到数
                int temp1 = num.pop();
                int temp2 = num.pop();
                char temp = (char)sym.pop();
                int result;
                switch (temp){
                    case '+': result = temp1 + temp2;break;
                    case '-': result = temp2 - temp1;break;
                    case '*': result = temp1 * temp2;break;
                    case '/': result = temp2 / temp1;break;
                    case '^': result = temp1 ^ temp2;break;
                    default:throw new RuntimeException("invalid symbol");
                }
                num.push(result);
                sym.push(a);
        }

    }
    //一个运算函数
    public int cal(int a, int b, int c){
        switch (b){
            case (int)'+': return a+c;
            case (int)'-': return a-c;
            case (int)'*': return a*c;
            case (int)'/': return a/c;
            case (int)'^': return a^c;
            default: throw new RuntimeException("wrong");
        }
    }

}
