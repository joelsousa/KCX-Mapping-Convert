/* ************************************************
 * Abgeänderte Klasse org.incava.util.diff.FileDiff
 * ************************************************/

package com.kewill.kcx.component.mapping.test.testUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.incava.util.diff.Diff;
import org.incava.util.diff.Difference;


/**
 * Modul		: FileDiff<br>
 * Erstellt		: 28.10.2008<br>
 * Beschreibung	: Vergleich zwei Dateien mitteld Diff. 
 *                Abgeänderte Klasse org.incava.util.diff.FileDiff
 * @author schmidt
 * @version 1.0.00
 */
public class FileDiff {
    private int differenceCount = 0;
    private List diffs = null;
    private boolean split = false;
    
    public static void main(String[] args) {
        if (args.length == 2) {
            new FileDiff(args[0], args[1]);
        } else {
            System.err.println("usage: org.incava.diffj.FileDiff from-file to-file");
        }
    }

    public FileDiff(String fromFile, String toFile, boolean split) {
        this.split = split;
        diff(fromFile, toFile);
    }
    
    public FileDiff(String fromFile, String toFile) {
        diff(fromFile, toFile);
    }

    private void diff(String fromFile, String toFile) {
        String[] aLines = read(fromFile);
        String[] bLines = read(toFile);
        diffs  = (new Diff(aLines, bLines)).diff();
        differenceCount = diffs.size();
        
        Iterator it     = diffs.iterator();
        while (it.hasNext()) {
            Difference diff     = (Difference) it.next();
            int        delStart = diff.getDeletedStart();
            int        delEnd   = diff.getDeletedEnd();
            int        addStart = diff.getAddedStart();
            int        addEnd   = diff.getAddedEnd();
            String     from     = toString(delStart, delEnd);
            String     to       = toString(addStart, addEnd);
            String     type     = delEnd != Difference.NONE && 
                                  addEnd != Difference.NONE ? "c" : (delEnd == Difference.NONE ? "a" : "d");

            System.out.println(from + type + to);

            if (delEnd != Difference.NONE) {
                printLines(delStart, delEnd, "<", aLines);
                if (addEnd != Difference.NONE) {
                    System.out.println("---");
                }
            }
            if (addEnd != Difference.NONE) {
                printLines(addStart, addEnd, ">", bLines);
            }
        }
    }
    
    protected void printLines(int start, int end, String ind, String[] lines) {
        for (int lnum = start; lnum <= end; ++lnum) {
            System.out.println(ind + " " + lines[lnum]);
        }
    }

    protected String toString(int start, int end) {
        // adjusted, because file lines are one-indexed, not zero.

        StringBuffer buf = new StringBuffer();

        // match the line numbering from diff(1):
        buf.append(end == Difference.NONE ? start : (1 + start));
        
        if (end != Difference.NONE && start != end) {
            buf.append(",").append(1 + end);
        }
        return buf.toString();
    }

    protected String[] read(String fileName) {
        try {
            BufferedReader br  = new BufferedReader(new FileReader(fileName));
            List<String> contents = new ArrayList<String>();
            String in;
            while ((in = br.readLine()) != null) {
                if (split) {
                    String[] lines = in.trim().replaceAll("><", ">\r\n  <").split("\r\n");
                    for (int i = 0; i < lines.length; i++) {
                        contents.add(lines[i].trim());
                    }
                } else {
                    contents.add(in.trim());
                }
            }
            return (String[]) contents.toArray(new String[] {});
        } catch (Exception e) {
            System.err.println("error reading " + fileName + ": " + e);
            System.exit(1);
            return null;
        }        
    }

    public int getDifferenceCount() {
        return differenceCount;
    }

    public List getDiffs() {
        return diffs;
    }
}

