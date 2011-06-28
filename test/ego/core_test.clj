(ns ego.core-test
  (:use ego.core
        clojure.test))

(deftest ego-test
  (testing "split-id returns a vector of keyword type and string id"
    (is (= [:foo "1"] (split-id "foo-1"))))
  (testing "split-id can take a function"
    (is (= [:foo "1"] (split-id "foo-1" #{:foo}))))
  (testing "split-id throws an exception when function returns false"
    (is (thrown-with-msg? Exception #"doesn't match" (split-id "foo-1" #{:bar})))
    (is (thrown-with-msg? Exception #"was not what" (split-id "foo-1" (constantly false))))))
