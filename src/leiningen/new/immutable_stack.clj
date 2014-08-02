(ns leiningen.new.immutable-stack
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "immutable-stack"))

(defn immutable-stack
  "template with weasel repl and server with sente websockets"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' immutable-stack project.")

    (->files data
             ["project.clj" (render "project.clj" data)]
             ["profiles.clj" (render "profiles.clj" data)]

             ["src/clj/{{sanitized}}/core.clj" (render "core.clj" data)]
             ["src/clj/{{sanitized}}/server.clj" (render "server.clj" data)]
             ["src/clj/{{sanitized}}/websocket.clj" (render "websocket.clj" data)]

             ["resources/public/index.html" (render "index.html" data)]
             ["src/cljs/{{sanitized}}/core.cljs" (render "core.cljs" data)]
             ["src/cljs/{{sanitized}}/websocket.cljs" (render "websocket.cljs" data)]

             [".gitignore" (render "gitignore" data)])))
