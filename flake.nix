{
  description = "Springboot development environment.";

  inputs = {
    nixpkgs.url = "github:nixos/nixpkgs?ref=nixos-unstable";
  };

  outputs =
    { self, nixpkgs }:
    let
      system = "x86_64-linux";
      pkgs = import nixpkgs {
        inherit system;
        config.allowUnfreePredicate =
          pkg:
          builtins.elem (nixpkgs.lib.getName pkg) [
            "idea-ultimate"
          ];
      };
    in
    {
      devShells."x86_64-linux".default = pkgs.mkShell {
        buildInputs = [
          pkgs.gradle
          pkgs.jdk17
          pkgs.jetbrains.idea-ultimate
          pkgs.nodejs_22
          pkgs.pnpm
          pkgs.sqlite
        ];
        shellHook = ''
          export TMPDIR=/tmp

          if [ ! -d "node_modules" ]; then
            echo "node_modules directory not found. Running pnpm install..."
            pnpm install > pnpm.log 2>&1
            echo "node_modules already exists. Skipping pnpm install."
          fi

          if command -v idea-ultimate &>/dev/null; then
            echo "Starting IntelliJ IDEA Ultimate..."
            nohup idea-ultimate . > idea.log 2>&1 &
          else
            echo "IntelliJ IDEA Ultimate command not found."
          fi
        '';
      };

    };
}
