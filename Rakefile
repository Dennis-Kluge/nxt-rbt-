
task :default => 'build_and_upload'

task :build_and_upload do
  sh "nxjc HelloWorld.java"
  sh "nxjlink -o HelloWorld.nxj HelloWorld"
  sh "nxjupload -r HelloWorld.nxj"
  sh "nxj -r -o HelloWorld.nxj HelloWorld"
end
