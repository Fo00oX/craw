package at.ac.fhcampuswien.craw.cli.model;

import at.ac.fhcampuswien.craw.cli.cliTools.SystemWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestSystemWrapper extends SystemWrapper {
    private final List<Message> stdOut = new ArrayList<>();
    private final List<Message> errOut = new ArrayList<>();

    private void addString(List<Message> l, String message) {
        Message result;
        if (l.size() == 0 || l.get(l.size() - 1).finished) {
            result = new Message();
            l.add(result);
        } else
            result = l.get(l.size() - 1);
        result.entries.add(message);
    }

    @Override
    public void print(String message) {
        addString(stdOut, message);
    }

    @Override
    public void printError(String error) {
        addString(errOut, error);
    }

    private void addLine(List<Message> l, String message) {
        if (l.size() > 0 && !l.get(l.size() - 1).finished)
            l.get(l.size() - 1).finished = true;
        Message result = new Message();
        result.entries.add(message);
        result.finished = true;
        l.add(result);
    }

    @Override
    public void println(String message) {
        addLine(stdOut, message);
    }

    @Override
    public void printlnError(String error) {
        addLine(errOut, error);
    }

    /**
     * Pops the oldest message
     *
     * @return the oldest message
     */
    public String popStdOut() {
        Message m = stdOut.get(0);
        stdOut.remove(0);
        return m != null ? m.getEntry() : "";
    }

    /**
     * Pops the oldest error message
     *
     * @return the oldest error message
     */
    public String popErrOut() {
        Message m = errOut.get(0);
        errOut.remove(0);
        return m != null ? m.getEntry() : "";
    }

    private class Message {
        private List<String> entries = new ArrayList<>();
        private boolean finished = false;

        public String getEntry() {
            return entries.stream().collect(Collectors.joining());
        }
    }
}
