package com.qunar.task1.fifth;

/**
 * @author zgm
 * @description
 * @date 2018/11/22 0:09
 */
public abstract class AbstractOrder {

    //结果，每执行一个Linux命令，结果在Linux命令链中传递
    protected Result result = new Result();

    //下一个Linux命令
    protected AbstractOrder nextOrder;

    //所有的Linux命令都基于这个目录之下
    protected String rootPath = "src\\main\\java\\com\\qunar\\task1\\fifth\\";

    //需要执行的命令
    public String order;

    public void setNextOrder(AbstractOrder nextOrder) {
        this.nextOrder = nextOrder;
    }

    //执行Linux命令
    public Result executeOrder() {
        doOwnResponsibility();
        if (nextOrder != null) {
            nextOrder.result = this.result;
            this.result = nextOrder.executeOrder();
        }
        return result;
    }

    //执行自己独有的Linux命令
    abstract void doOwnResponsibility();

}
