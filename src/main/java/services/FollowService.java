package services;

import java.util.List;

import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import actions.views.FollowConverter;
import actions.views.FollowView;
import constants.JpaConst;
import models.Employee;
import models.Follow;


public class FollowService extends ServiceBase {


    /*
     * ログインしている従業員のフォローしている従業員のidをリストで取得する
     * */
    public List<FollowView> getFollowEmp(EmployeeView employee){
        List<Follow> follows = em.createNamedQuery(JpaConst.Q_FLW_GET_ALL_FLWEMP,Follow.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
                .getResultList();
        System.out.println("情報の取得にtryしました。");
        return FollowConverter.toViewList(follows);
    }


    /*
     * ログインしている従業員のフォローしている従業員のidをリストで取得する
     * */
    public List<Follow> getFollowEmp2(EmployeeView employee){
        List<Follow> follows = em.createNamedQuery(JpaConst.Q_FLW_GET_ALL_FLWEMP,Follow.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
                .getResultList();
        System.out.println("models情報の取得にtryしました。");
        return follows;
    }


    /*
     * codeを条件にデータを取得employeeView型で返す
     * */
    public EmployeeView findOneCode(String code) {
        Employee e = null;
        //社員番号を使って検索
        e = em.createNamedQuery(JpaConst.Q_FLW_SERCH,Employee.class)
                .setParameter(JpaConst.JPQL_PARM_CODE,code)
                .getSingleResult();

        return EmployeeConverter.toView(e);
    }

    public void create(FollowView fl) {
        createInternal(fl);
        System.out.println("登録処理を終えました。");
    }

    private void createInternal(FollowView fl) {
        em.getTransaction().begin();
        em.persist(FollowConverter.toModel(fl));
        em.getTransaction().commit();
    }





}
