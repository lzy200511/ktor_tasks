"""Send a GET query or a POST form to HTTPBin and display the exchange."""

import argparse
import sys

import httpx


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("method", choices=("GET", "POST"), nargs="?", default="GET")
    parser.add_argument("--email", default="student@example.com")
    args = parser.parse_args()
    url = "https://httpbin.io/dump/request"

    try:
        with httpx.Client(timeout=30.0) as client:
            if args.method == "GET":
                request = client.build_request("GET", url, params={"email": args.email})
            else:
                request = client.build_request("POST", url, data={"email": args.email})
            print(f"> {request.method} {request.url}")
            for name, value in request.headers.items():
                print(f"> {name}: {value}")
            if request.content:
                print(f"\n{request.content.decode('utf-8')}")
            response = client.send(request)
            print(f"\n< {response.http_version} {response.status_code}")
            for name, value in response.headers.items():
                print(f"< {name}: {value}")
            print(f"\n{response.text}")
            response.raise_for_status()
    except httpx.HTTPError as error:
        print(f"Request failed: {error}", file=sys.stderr)
        return 1
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
