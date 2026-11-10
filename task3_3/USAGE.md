# Task 3 usage

## Task 3.1: local server

From the repository root:

```sh
cd task3_1
python -m http.server
```

Use `python3` if that is your Python command. Open
http://localhost:8000/test.html (the task lists http://0.0.0.0:8000/test.html).
The terminal should log the GET request. Stop the server with Ctrl+C.

## Task 3.2: browser forms

Open `../task3_2/form.html` for the original GET form or
`../task3_2/form-post.html` for the POST version. Both submit the same
`email` field to the required HTTPBin endpoint.

GET places the URL-encoded field in the query string. POST sends it in the
request body using `application/x-www-form-urlencoded`. Browser developer
tools' Network panel can show the method, URL, headers, and form data.

## Task 3.3: uv and HTTPX

Install uv using https://docs.astral.sh/uv/getting-started/installation/.
From `task3_3`, run:

```sh
uv sync
uv run httpx --help
uv run httpx https://httpbin.io/dump/request -m GET -p email student@example.com
uv run httpx https://httpbin.io/dump/request -m POST -d email student@example.com
```

The committed project configuration supplies the project and dependency
declarations normally created by `uv init --vcs none` and
`uv add 'httpx[cli]'`. Do not reinitialize the existing project.
`uv sync` creates the local environment and generates `uv.lock`.

An additional Python example prints the outgoing request and incoming response:

```sh
uv run main.py GET --email student@example.com
uv run main.py POST --email student@example.com
```

These commands make real requests only when run. No browser checks, request
experiments, or dependency installation were performed for this submission.

The README-linked course guide was inaccessible during implementation.
The files cover the repository's explicit instructions and provide GET/POST
examples; additional guide-only requirements could not be verified.
