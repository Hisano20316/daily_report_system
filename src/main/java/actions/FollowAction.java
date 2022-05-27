package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.EmployeeView;
import actions.views.FollowView;
import constants.AttributeConst;
import constants.ForwardConst;
import models.Follow;
import services.FollowService;

public class FollowAction extends ActionBase {

    private FollowService service;

    /**
     * メソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        service = new FollowService();

        //メソッドを実行
        invoke();
        service.close();
    }


    /**
     * 一覧画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void index() throws ServletException, IOException {


        //セッションからログイン中の従業員情報を取得
        EmployeeView loginEmployee = (EmployeeView)getSessionScope(AttributeConst.LOGIN_EMP);

        //従業員のフォローリストのデータを取得
        List<FollowView> follows = service.getFollowEmp(loginEmployee);
        List<Follow> followMdels = service.getFollowEmp2(loginEmployee);
        //putRequestScope(AttributeConst.FOLLOWS, follows);
        System.out.println("index処理が完了しました。");
        System.out.println(follows);
        System.out.println(followMdels);

        putRequestScope(AttributeConst.LOGIN_EMP,loginEmployee);
        putRequestScope(AttributeConst.FOLLOW,follows);
        //putRequestScope(AttributeConst.FOLLOWS,follows);
        //System.out.println(follows);


        //フラッシュメッセージ
        String flush = getSessionScope(AttributeConst.FLUSH);
        if(flush != null) {
            putRequestScope(AttributeConst.FLUSH,flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        forward(ForwardConst.FW_FLW_INDEX);
    }

    /*
     * 検索画面を表示する
     * */
    public void serch() throws ServletException,IOException{

        //検索画面を表示
        forward(ForwardConst.FW_FLW_SERCH);
    }

    /*
     * 検索結果を表示
     * */
    public void numserch() throws ServletException,IOException{
        String code = getRequestParam(AttributeConst.EMP_CODE);//"code"従業員番号
        EmployeeView ev = service.findOneCode(code);

        //evに従業員番号から検索したオブジェクトが入っている


        putRequestScope(AttributeConst.EMPLOYEE,ev);//employeeという名で社員情報を登録
        putSessionScope(AttributeConst.EMPLOYEE,ev);
        putRequestScope(AttributeConst.TESTVALUE,code);

            //画面を表示
            forward(ForwardConst.FW_FLW_SERCH);


    }


    public void follow_do() throws ServletException, IOException {
        //セッションスコープからログインしている社員オブジェクトを取得 model型のままでいい
        EmployeeView loginEmp = (EmployeeView)getSessionScope(AttributeConst.LOGIN_EMP);
        //リクエストスコープからemployee（検索した社員情報）を取得
        EmployeeView emp =  (EmployeeView)getSessionScope(AttributeConst.EMPLOYEE);


        //パラメータの値をもとにfollowsテーブルのインスタンスを作成する
        FollowView fl = new FollowView(null,loginEmp,emp);
        //データの登録
        service.create(fl);
        System.out.println("処理を終えました。");
        removeSessionScope(AttributeConst.EMPLOYEE);
        redirect(ForwardConst.ACT_FLW,ForwardConst.CMD_INDEX);


    }




}
