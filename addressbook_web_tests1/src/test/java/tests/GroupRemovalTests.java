package tests;


import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        if (app.groups().getCountGroups() == 0) {
            app.groups().createGroup(new GroupData("group name", "group header", "group footer"));
        }
        int groupCount = app.groups().getCountGroups();
        app.groups().removeGroup();
        int newGroupCount = app.groups().getCountGroups();
        Assertions.assertEquals(groupCount - 1,newGroupCount);
    }

    @Test
    void canRemoveAllGroupsAtOnce() {
        if (app.groups().getCountGroups() == 0) {
            app.groups().createGroup(new GroupData("group name", "group header", "group footer"));
        }
        app.groups().removeAllGroups();
        Assertions.assertEquals(0, app.groups().getCountGroups());
    }
}
