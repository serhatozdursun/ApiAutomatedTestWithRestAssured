import Api.ListUserApi;
import org.junit.Test;

public class CoreTest {

    @Test
    public void coreTest(){
        ListUserApi lua = new ListUserApi();
        lua.getListUsers();
    }
}
