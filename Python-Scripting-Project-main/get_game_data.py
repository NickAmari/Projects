import os
import json
import shutil
from subprocess import PIPE, run
import sys

GAME_DIR_PATTERN = "game"
GAME_CODE_EXTENTION = ".go"
GAME_COMPILE_COMMAND = ["go", "build"]


def find_all_game_paths(source):
    game_paths = []

    # DFS all files starting at 'source' directory
    for root, dirs, files in os.walk(source):
        for directory in dirs:
            # foreach directory, if its name contins 'game' generate string with source path and add path to list
            if GAME_DIR_PATTERN in directory.lower():
                path = os.path.join(source, directory)
                game_paths.append(path)

        break

    return game_paths


def get_name_from_paths(paths, to_strip):
    new_names = []
    for path in paths:
        # splits the final portion of a path ex. C://stuff/here -> here
        _, dir_name = os.path.split(path)
        # replaces here_game -> here""
        new_dir_name = dir_name.replace(to_strip, "")
        new_names.append(new_dir_name)

    return new_names


def create_dir(path):
    if not os.path.exists(path):
        os.mkdir(path)


def copy_and_overwrite(source, dest):
    if os.path.exists(dest):  # if there exists a destination path
        shutil.rmtree(dest)  # delete all dirs/files within destination
    # copy all files/dirs from source into destination
    shutil.copytree(source, dest)


def make_json_metadata(path, game_dirs):
    data = {
        "gameNames": game_dirs,
        "numberOfGames": len(game_dirs)
    }
    with open(path, "w") as f:  # 'with' keyword handles scoping file, opens it, performs code, then closes it and exists the with statement
        json.dump(data, f)


def compile_game_code(path):
    code_file_name = None
    for root, dirs, files in os.walk(path):
        for file in files:
            if file.endswith(GAME_CODE_EXTENTION):
                code_file_name = file
                break

        break

    if code_file_name is None:
        return

    command = GAME_COMPILE_COMMAND + [code_file_name]
    run_command(command, path)


def run_command(command, path):
    cwd = os.getcwd()
    os.chdir(path)

    result = run(command, stdout=PIPE, stdin=PIPE, universal_newlines=True)
    print(result)

    os.chdir(cwd)


def main(source, target):
    cwd = os.getcwd()  # string to path in current working directory where code is called
    source_path = os.path.join(cwd, source)
    target_path = os.path.join(cwd, target)

    game_paths = find_all_game_paths(source_path)
    new_game_dirs = get_name_from_paths(game_paths, "_game")

    create_dir(target_path)

    # Zip takes matching elements from arrays and combines them in a tuple based on index
    for src, dest in zip(game_paths, new_game_dirs):
        dest_path = os.path.join(target_path, dest)
        copy_and_overwrite(src, dest_path)
        compile_game_code(dest_path)

    json_path = os.path.join(target_path, "metadata.json")
    make_json_metadata(json_path, new_game_dirs)


if __name__ == "__main__":
    args = sys.argv  # store commandline arguments
    if len(args) != 3:
        raise Exception("You much pass a source and target directory - only.")

    # stores 2 variables at the 1st through index (in this case only)
    source, target = args[1:]
    main(source, target)
