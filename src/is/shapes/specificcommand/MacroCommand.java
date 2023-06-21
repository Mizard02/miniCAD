package is.shapes.specificcommand;

import is.command.Command;

public class MacroCommand implements Command {

    private Command cmd1;
    private Command cmd2;

    public MacroCommand(Command cmd1, Command cmd2) {
        this.cmd1 = cmd1;
        this.cmd2 = cmd2;
    }

    @Override
    public boolean doIt() {
        cmd1.doIt();
        cmd2.doIt();
        return true;
    }

    @Override
    public boolean undoIt() {
        cmd1.undoIt();
        cmd2.undoIt();
        return true;
    }
}
