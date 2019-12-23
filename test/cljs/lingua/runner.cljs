(ns lingua.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [lingua.core-test]))

(doo-tests 'lingua.core-test)
