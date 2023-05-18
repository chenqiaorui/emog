# push_code() {
#     git add . && \
#     git commit -m"update" \
#     git push 
# }
# depoly() {
#     hexo clean && \ 
#     hexo g && \
#     hexo d
# }

# if [ $1 == 'push' ]
#     push_code
# fi

# deploy
hexo clean && \ 
    hexo g && \
    hexo d