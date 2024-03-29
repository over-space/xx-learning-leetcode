package com.learning.leetcode;

import com.learning.BaseTest;
import org.testng.Assert;

import java.util.Arrays;

/**
 * 208. 实现 Trie (前缀树)
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 *
 * 请你实现 Trie 类：
 *
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lifang
 * @since 2021/10/19
 */
public class Leetcode0208_trie extends BaseTest implements Testing {

    @Override
    public void test() {
        Trie trie = new Trie();
        trie.insert("word");
        trie.insert("hello");
        trie.insert("card");
        trie.insert("time");

        Assert.assertTrue(trie.startsWith("ti"));
        Assert.assertFalse(trie.startsWith("tm"));
        Assert.assertTrue(trie.startsWith("wor"));
        Assert.assertTrue(trie.search("word"));
        Assert.assertFalse(trie.search("hel"));
    }

    class Trie {

        private Trie[] children;

        private boolean isEnd;

        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }

        public void insert(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                int index = ch - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new Trie();
                }
                node = node.children[index];
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            Trie trie = startsWithPrefix(word);
            return trie != null && trie.isEnd;
        }

        public boolean startsWith(String prefix) {
            return startsWithPrefix(prefix) != null;
        }

        private Trie startsWithPrefix(String prefix){
            Trie node = this;
            for (int i = 0; i < prefix.length(); i++) {
                char ch = prefix.charAt(i);
                int index = ch - 'a';
                if((node = node.children[index]) == null){
                    return null;
                }
            }
            return node;
        }

        @Override
        public String toString() {
            return "Trie{" +
                    "children=" + Arrays.toString(children) +
                    ", isEnd=" + isEnd +
                    '}';
        }
    }
}
